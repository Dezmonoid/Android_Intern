package com.example.android_intern

object WeatherStore {
    private var weathers: List<ForecastResponse.Sky?>? = null

    fun get() = weathers
    fun set(value: List<ForecastResponse.Sky>?) {
        weathers = value
    }
}