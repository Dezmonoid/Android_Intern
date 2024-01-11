package com.example.android_intern

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

interface WeatherApi {
    companion object {
        fun create(): WeatherApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(WeatherApi::class.java)
        }
    }

    @GET("forecast?")
    suspend fun getCurrentForecastData(
        @Query("id") cityId: String,
        @Query("APPID") appId: String,
        @Query("units") units: String
    ): ForecastResponse
}