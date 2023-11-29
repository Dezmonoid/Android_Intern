package com.example.android_intern

import android.app.Application

class App : Application() {
    companion object {
        lateinit var apiService: RickAndMortyApi
            private set
    }

    override fun onCreate() {
        super.onCreate()
        apiService = RickAndMortyApi.initApiService()
    }
}