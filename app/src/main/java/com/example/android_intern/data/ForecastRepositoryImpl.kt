package com.example.android_intern.data

import com.example.android_intern.domain.ForecastRepository
import com.example.android_intern.domain.model.ForecastModel
import com.example.android_intern.domain.model.ForecastType

private const val APP_ID = "eeba719e0ea1ed0d70d6ea433307695e"
private const val UNITS = "metric"
private const val CITY_ID = "622034"

class ForecastRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val weatherApi: WeatherApi
) : ForecastRepository {
    override suspend fun getForecast(): ForecastModel {
        val forecastDao = appDatabase.forecastDao()
        return try {
            val response = weatherApi.getCurrentForecastData(
                cityId = CITY_ID,
                appId = APP_ID,
                units = UNITS
            )
            val forecast = ForecastModel(
                forecast = response.list?.map { it.toDomain() }.orEmpty(),
                forecastType = ForecastType.Network
            )
            forecastDao.insertToDatabase(forecast.forecast.map { it.toDB() })
            forecast
        } catch (e: Exception) {
            ForecastModel(
                forecast = forecastDao.getAll().map { it.toDomain() },
                forecastType = ForecastType.DataBase
            )
        }
    }
}


