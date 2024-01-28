package com.example.android_intern.data

import com.example.android_intern.data.model.db.ForecastDB
import com.example.android_intern.data.model.nw.ForecastNW
import com.example.android_intern.domain.model.Forecast

fun ForecastNW.Sky.toDomain(): Forecast =
    Forecast(
        dtTxt = dtTxt.toString(),
        temp = main?.temp.toZeroIfNull(),
        icon = weather?.first()?.icon.toString()
    )

fun ForecastDB.toDomain(): Forecast =
    Forecast(
        dtTxt = dtTxt.toString(),
        temp = temp.toZeroIfNull(),
        icon = icon.toString()
    )

fun Forecast.toDB(): ForecastDB =
    ForecastDB(
        dtTxt = dtTxt,
        temp = temp,
        icon = icon
    )

private fun Double?.toZeroIfNull(): Double = this ?: 0.0