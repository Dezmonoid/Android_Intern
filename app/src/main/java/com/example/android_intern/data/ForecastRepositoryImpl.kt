package com.example.android_intern.data

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
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
    private val regionDatabase: RegionDatabase,
    private val weatherApi: WeatherApi,
    private val fusedLocationClient: FusedLocationProviderClient
) : ForecastRepository {
    override suspend fun getRegion() {
        val regionDao = regionDatabase.regionDao()
        val location = getLocation()
        Log.e("1", "1")
        val response = weatherApi.getCurrentRegionData(
            lat = location?.latitude.toZeroIfNull(),
            lon = location?.longitude.toZeroIfNull(),
            appId = APP_ID
        )
        Log.e("2", "2")
        regionDao.insertToRegionDatabase(response.first().toDB())
    }

    override suspend fun getForecast(): ForecastModel {
        val forecastDao = appDatabase.forecastDao()
        val regionDao = regionDatabase.regionDao()
        val region = regionDao.getRegion()
        Log.e("3", "3")
        return try {
            val response = weatherApi.getCurrentForecastData(
                lat = region.lat,
                lon = region.lon,
                appId = APP_ID,
                units = UNITS
            )
            val forecast = ForecastModel(
                forecast = response.list?.map { it.toDomain() }.orEmpty(),
                forecastType = ForecastType.Network,
                region = region.name.toString()
            )
            forecastDao.insertToDatabase(forecast.forecast.map { it.toDB() })
            Log.e("4", "4")
            forecast
        } catch (e: Exception) {
            ForecastModel(
                forecast = forecastDao.getAll().map { it.toDomain() },
                forecastType = ForecastType.DataBase,
                region = region.name.toString()
            )
        }
    }

    @SuppressLint("MissingPermission")
    private suspend fun getLocation() = suspendCancellableCoroutine { continuation ->
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


