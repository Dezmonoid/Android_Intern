package com.example.android_intern

import android.util.Log
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ForecastViewModel : ViewModel() {
    private lateinit var apiService: WeatherApi
    private var forecastList: List<ForecastResponse.Sky>? = null

    init{
        initApiService()
        Log.i(TAG,"Инициализация")
        Log.i(TAG,Thread.currentThread().toString())
    }

    private fun initApiService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(WeatherApi::class.java)
    }

    fun loadWeather(){
        apiService.getCurrentForecastData(cityId = CITY_ID, appId = APP_ID, units = UNITS)
            .enqueue(object : Callback<ForecastResponse> {
                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    forecastList = response.body()?.list
                    Log.i(TAG,"Получил список:${forecastList.toString()}")
                    Log.i(TAG,Thread.currentThread().toString())

                }

                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    Log.e(TAG, t.message, t)
                }
            })
    }
    fun getForecast() = forecastList

}