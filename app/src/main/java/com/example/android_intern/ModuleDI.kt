package com.example.android_intern

import android.app.Application
import androidx.room.Room
import com.example.android_intern.data.AppDatabase
import com.example.android_intern.data.ForecastRepositoryImpl
import com.example.android_intern.data.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleDI : Application() {

    @Provides
    @Singleton
    fun initWeatherAPI(): WeatherApi = WeatherApi.create()

    @Provides
    @Singleton
    fun initAppDataBase(): AppDatabase =
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "forecast").build()

    @Provides
    @Singleton
    fun initRepository(appDatabase: AppDatabase, weatherApi: WeatherApi): ForecastRepositoryImpl =
        ForecastRepositoryImpl(appDatabase, weatherApi)
}