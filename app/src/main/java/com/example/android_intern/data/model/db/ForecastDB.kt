package com.example.android_intern.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast")
data class ForecastDB(
    @PrimaryKey(true)
    val id: Int? = null,
    @ColumnInfo(name = "dt")
    val dtTxt: String?,
    @ColumnInfo(name = "tmp")
    val temp: Double?,
    @ColumnInfo(name = "icon")
    val icon: String?
)