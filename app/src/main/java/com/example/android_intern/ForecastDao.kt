package com.example.android_intern

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ForecastDao {

    @Query("SELECT * FROM forecastdataclass")
    suspend fun getAll(): List<ForecastDataClass>

    @Query("DELETE FROM forecastdataclass")
    suspend fun deleteAll()

    @Query("SELECT * FROM forecastdataclass WHERE id IN (:id)")
    suspend fun getById(id: IntArray): ForecastDataClass

    @Query("INSERT INTO forecastdataclass (dt,tmp,icon) VALUES (:dt,:tmp,:icon)")
    suspend fun insertForecast(dt:String,tmp:Double,icon:String)


}