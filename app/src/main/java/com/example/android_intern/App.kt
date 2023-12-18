package com.example.android_intern

import android.app.Application
import androidx.room.Room

class App : Application() {
    companion object {
        lateinit var weatherApi: WeatherApi
            private set
        lateinit var db: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        weatherApi = WeatherApi.create()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "forecast_database"
        ).build()
    }
}