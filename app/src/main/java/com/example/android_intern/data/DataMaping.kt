package com.example.android_intern.data

import com.example.android_intern.data.model.db.ForecastDB
import com.example.android_intern.data.model.nw.ForecastNW
import com.example.android_intern.domain.model.Forecast

fun ForecastNW.Sky.toDomain(): Forecast =
    Forecast(
        dtTxt = this.dtTxt.toString(),
        temp = this.main?.temp ?: 0.0,
        icon = this.weather?.first()?.icon.toString()
    )

fun ForecastDB.toDomain(): Forecast =
    Forecast(
        dtTxt = this.dtTxt.toString(),
        temp = this.temp ?: 0.0,
        icon = this.icon.toString()
    )

fun Forecast.toForecastDB(): ForecastDB =
    ForecastDB(
        dtTxt = this.dtTxt,
        temp = this.temp,
        icon = this.icon
    )