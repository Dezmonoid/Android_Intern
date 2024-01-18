package com.example.android_intern.data

import com.example.android_intern.data.model.db.ForecastDB
import com.example.android_intern.data.model.nw.ForecastNW
import com.example.android_intern.domain.model.Forecast

fun ForecastNW.Sky.toDomain(): Forecast =
    Forecast(
        dtTxt = this.dtTxt.toString(),
        temp = this.main?.temp.toZeroIfNull(),
        icon = this.weather?.first()?.icon.toString()
    )

fun ForecastDB.toDomain(): Forecast =
    Forecast(
        dtTxt = this.dtTxt.toString(),
        temp = this.temp.toZeroIfNull(),
        icon = this.icon.toString()
    )

fun Forecast.toDB(): ForecastDB =
    ForecastDB(
        dtTxt = this.dtTxt,
        temp = this.temp,
        icon = this.icon
    )

private fun Double?.toZeroIfNull(): Double = this ?: 0.0