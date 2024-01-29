package com.example.android_intern.data

import android.annotation.SuppressLint
import android.location.Location
import com.example.android_intern.domain.ForecastRepository
import com.example.android_intern.domain.model.ForecastModel
import com.example.android_intern.domain.model.ForecastType
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

private const val APP_ID = "eeba719e0ea1ed0d70d6ea433307695e"
private const val UNITS = "metric"

class ForecastRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val locationDatabase: LocationDatabase,
    private val weatherApi: WeatherApi,
    private val fusedLocationClient: FusedLocationProviderClient
) : ForecastRepository {
    private val forecastDao = appDatabase.forecastDao()
    private val locationDao = locationDatabase.locationDao()
    override suspend fun getLocation() {
        val location = getCoordinates()
        val response = weatherApi.getCurrentLocationData(
            lat = location?.latitude.toZeroIfNull(),
            lon = location?.longitude.toZeroIfNull(),
            appId = APP_ID
        )
        locationDao.insertToLocationDatabase(response.first().toDB())
    }

    override suspend fun getForecast(): ForecastModel {
        val location = locationDao.getLocation()
        return try {
            val response = weatherApi.getCurrentForecastData(
                lat = location.lat,
                lon = location.lon,
                appId = APP_ID,
                units = UNITS
            )
            val forecast = ForecastModel(
                forecast = response.list?.map { it.toDomain() }.orEmpty(),
                forecastType = ForecastType.Network,
                location = location.name.toString()
            )
            forecastDao.insertToDatabase(forecast.forecast.map { it.toDB() })
            forecast
        } catch (e: Exception) {
            ForecastModel(
                forecast = forecastDao.getAll().map { it.toDomain() },
                forecastType = ForecastType.DataBase,
                location = location.name.toString()
            )
        }
    }

    @SuppressLint("MissingPermission")
    private suspend fun getCoordinates() = suspendCancellableCoroutine { continuation ->
        fusedLocationClient.lastLocation
            .addOnSuccessListener { lastKnownLocation: Location? ->
                if (lastKnownLocation != null) {
                    continuation.resume(lastKnownLocation)
                } else {
                    val cancellationTokenSource = CancellationTokenSource()
                    fusedLocationClient.getCurrentLocation(
                        LocationRequest.PRIORITY_HIGH_ACCURACY,
                        cancellationTokenSource.token
                    ).addOnSuccessListener { location: Location? ->
                        cancellationTokenSource.cancel()
                        continuation.resume(location)
                    }.addOnFailureListener { exception ->
                        cancellationTokenSource.cancel()
                        continuation.resumeWithException(exception)
                    }
                }
            }
            .addOnFailureListener { exception ->
                continuation.resumeWithException(exception)
            }
    }
}


