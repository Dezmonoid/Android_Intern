package com.example.android_intern.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.android_intern.data.model.db.LocationDB

@Dao
abstract class LocationDao() {
    @Query("SELECT * FROM region")
    abstract suspend fun getLocation(): LocationDB

    @Query("DELETE FROM region")
    abstract suspend fun deleteLocation()

    @Insert
    abstract suspend fun insertLocation(location: LocationDB)

    @Transaction
    open suspend fun insertToLocationDatabase(location: LocationDB) {
        deleteLocation()
        insertLocation(location)
    }
}