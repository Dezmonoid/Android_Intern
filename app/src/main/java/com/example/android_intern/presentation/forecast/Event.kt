package com.example.android_intern.presentation.forecast

import com.example.android_intern.domain.model.ForecastModel
import com.example.android_intern.domain.model.ForecastType

open class Event<out T>(private val forecastModel: ForecastModel) {

    private var hasBeenHandled = false

    fun getEventForecastType(): Boolean {
        return when (hasBeenHandled) {
            true -> false
            false -> {
                when (forecastModel.forecastType) {
                    ForecastType.DataBase -> {
                        hasBeenHandled = true
                        true
                    }

                    ForecastType.Network -> false
                }
            }
        }
    }

    fun getCityName(): String {
        return forecastModel.city
    }
}