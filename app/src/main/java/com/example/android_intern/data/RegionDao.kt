package com.example.android_intern.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.android_intern.data.model.db.RegionDB

    @Dao
    abstract class RegionDao() {

        @Query("SELECT * FROM region")
        abstract suspend fun getRegion(): RegionDB

        @Query("DELETE FROM region")
        abstract suspend fun deleteRegion()

        @Insert
        abstract suspend fun insertRegion(forecasts: RegionDB)

        @Transaction
        open suspend fun insertToRegionDatabase(region: RegionDB) {
            deleteRegion()
            insertRegion(region)
        }
    }