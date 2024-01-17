package com.example.android_intern

import android.app.Application
import androidx.room.Room
import com.example.android_intern.data.AppDatabase
import com.example.android_intern.data.ForecastRepositoryImpl
import com.example.android_intern.data.WeatherApi
import com.example.android_intern.domain.ForecastRepository

class App : Application() {
    companion object {
        lateinit var repository: ForecastRepository
            private set
    }

    private lateinit var weatherApi: WeatherApi
    private lateinit var appDatabase: AppDatabase
    override fun onCreate() {
        super.onCreate()
        initAppDataBase()
        initWeatherApi()
        initRepository()
    }

    private fun initRepository() {
        repository = ForecastRepositoryImpl(appDatabase, weatherApi)
    }

    private fun initAppDataBase() {
        appDatabase =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "forecast").build()
    }

    private fun initWeatherApi() {
        weatherApi = WeatherApi.create()
    }
}