package com.example.android_intern

import android.app.Application
import com.example.android_intern.data.ForecastRepositoryImpl
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {
    companion object{
        @Inject
        lateinit var repository: ForecastRepositoryImpl
        private set
    }
}