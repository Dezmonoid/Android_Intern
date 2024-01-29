package com.example.android_intern.data

import com.example.android_intern.data.model.nw.ForecastNW
import com.example.android_intern.data.model.nw.RegionNW
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("geo/1.0/reverse?")
    suspend fun getCurrentRegionData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("APPID") appId: String
    ): List<RegionNW>

    @GET("data/2.5/forecast?")
    suspend fun getCurrentForecastData(
        //@Query("id") cityId: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("APPID") appId: String,
        @Query("units") units: String
    ): ForecastNW
}