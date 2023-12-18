package com.example.android_intern

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ForecastDataClass::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun forecastDao(): ForecastDao
}