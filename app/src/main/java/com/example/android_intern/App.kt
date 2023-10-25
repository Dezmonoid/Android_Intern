package com.example.android_intern

import android.app.Application

class App:Application() {
    companion object{
        lateinit var weatherApi:WeatherApi
            private set
    }

    override fun onCreate() {
        super.onCreate()
        weatherApi = WeatherApi.create()
    }
}