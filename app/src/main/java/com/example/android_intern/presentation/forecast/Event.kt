package com.example.android_intern.presentation.forecast

import android.view.View
import com.example.android_intern.domain.model.ForecastType
import com.google.android.material.snackbar.Snackbar

const val NOT_AVAILABLE = "Сервер сейчас не доступен"

open class Event<out T>(private val forecastType: T) {
    var hasBeenHandled = false
        private set

    fun getContentEvent(view: View) {
        if (hasBeenHandled.not()) {
            when (forecastType) {
                ForecastType.DataBase -> {
                    Snackbar.make(view, NOT_AVAILABLE, Snackbar.LENGTH_LONG)
                        .show()
                    hasBeenHandled = true
                }
            }
        }
    }
}