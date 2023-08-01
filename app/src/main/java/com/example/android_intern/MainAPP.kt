package com.example.android_intern

import android.app.Application
import timber.log.Timber

class MainAPP:Application() {
    override fun onCreate() {
        Timber.plant(Timber.DebugTree())
        super.onCreate()
    }
}