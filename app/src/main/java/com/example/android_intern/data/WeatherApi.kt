package com.example.android_intern.data

import com.example.android_intern.data.model.nw.ForecastNW
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
                .client(createOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(WeatherApi::class.java)
        }

        private fun createOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(createLoggingInterceptor())
                .build()
        }

        private fun createLoggingInterceptor(): Interceptor {
            return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @GET("forecast?")
    suspend fun getCurrentForecastData(
        @Query("id") cityId: String,
        @Query("APPID") appId: String,
        @Query("units") units: String
    ): ForecastNW
}