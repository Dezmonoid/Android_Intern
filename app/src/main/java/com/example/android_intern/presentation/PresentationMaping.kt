package com.example.android_intern.presentation

import com.example.android_intern.domain.model.Forecast
import com.example.android_intern.presentation.model.ForecastUI

fun Forecast.toForecastUI(): ForecastUI =
    ForecastUI(
        dtTxt = this.dtTxt,
        temp = this.temp,
        icon = this.icon
    )
