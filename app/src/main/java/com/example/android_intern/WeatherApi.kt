package com.example.android_intern


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast?")
    fun getCurrentForecastData(
        @Query("id") city_id: String,
        @Query("APPID") app_id: String,
        @Query("units") units: String
    ): Call<ForecastResponse>
}