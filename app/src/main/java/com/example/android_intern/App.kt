package com.example.android_intern

import android.app.Application
import androidx.room.Room
import com.example.android_intern.data.AppDatabase
import com.example.android_intern.data.ForecastRepositoryImpl
import com.example.android_intern.data.WeatherApi
import com.example.android_intern.domain.ForecastRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
@HiltAndroidApp
class App : Application()