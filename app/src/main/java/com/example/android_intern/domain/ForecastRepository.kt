package com.example.android_intern.domain

import com.example.android_intern.domain.model.ForecastModel

interface ForecastRepository {
    suspend fun getForecast(): ForecastModel
    suspend fun getLocation()
}

