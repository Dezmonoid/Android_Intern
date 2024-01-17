package com.example.android_intern.domain

import com.example.android_intern.domain.model.Forecast


interface ForecastRepository {
    suspend fun getForecast(): List<Forecast>
}

