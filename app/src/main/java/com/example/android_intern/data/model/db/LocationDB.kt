package com.example.android_intern.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "region")
class LocationDB(
    @PrimaryKey(true)
    val id: Int? = null,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "lat")
    val lat: Double,
    @ColumnInfo(name = "lon")
    val lon: Double
)