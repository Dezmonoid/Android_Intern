package com.example.android_intern.presentation

import com.example.android_intern.domain.model.Forecast
import com.example.android_intern.presentation.model.ForecastUI
import kotlin.math.round

fun Forecast.toUI(): ForecastUI =
    ForecastUI(
        dtTxt = dtTxt,
        temp = round(temp).toInt() ,
        icon = icon
    )
