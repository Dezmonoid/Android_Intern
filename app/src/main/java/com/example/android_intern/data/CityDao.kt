package com.example.android_intern.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.android_intern.data.model.db.CityDB

@Dao
abstract class CityDao() {
    @Query("SELECT * FROM region")
    abstract suspend fun getCity(): CityDB

    @Query("DELETE FROM region")
    abstract suspend fun deleteCity()

    @Insert
    abstract suspend fun insertCity(city: CityDB)

    @Transaction
    open suspend fun insertToCityDatabase(city: CityDB) {
        deleteCity()
        insertCity(city)
    }
}