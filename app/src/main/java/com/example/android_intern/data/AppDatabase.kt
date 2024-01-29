package com.example.android_intern.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android_intern.data.model.db.ForecastDB
import com.example.android_intern.data.model.db.RegionDB

@Database(entities = [ForecastDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun forecastDao(): ForecastDao
}

@Database(entities = [RegionDB::class], version = 1)
abstract class RegionDatabase : RoomDatabase() {
    abstract fun regionDao(): RegionDao
}

