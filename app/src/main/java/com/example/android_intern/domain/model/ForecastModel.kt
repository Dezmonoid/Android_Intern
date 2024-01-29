package com.example.android_intern.domain.model

class ForecastModel(
    val forecast: List<Forecast>,
    val forecastType: ForecastType,
    val location: String
)

enum class ForecastType() {
    DataBase,
    Network
}

data class Forecast(
    val dtTxt: String,
    val temp: Double,
    val icon: String
)