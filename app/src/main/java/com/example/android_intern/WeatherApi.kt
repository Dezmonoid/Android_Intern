package com.example.android_intern


import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

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
    fun getCurrentForecastData(
        @Query("id") cityId: String,
        @Query("APPID") appId: String,
        @Query("units") units: String
    ): Call<ForecastResponse>
}