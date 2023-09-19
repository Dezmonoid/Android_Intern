package com.example.android_intern

object WeatherStore {
    private var weathers: ForecastResponse? = null

    fun get() = weathers
    fun set(value: ForecastResponse?) {
        weathers = value
    }
}