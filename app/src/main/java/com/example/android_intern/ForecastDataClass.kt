package com.example.android_intern

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ForecastDataClass(
    @PrimaryKey(true)
    val id:Int?,
    @ColumnInfo(name = "dt")
    val dtTxt: String?,
    @ColumnInfo(name = "tmp")
    val temp: Double?,
    @ColumnInfo(name = "icon")
    val icon: String?
)