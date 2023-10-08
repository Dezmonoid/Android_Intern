package com.example.android_intern

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ForecastViewModel : ViewModel() {
    private lateinit var apiService: WeatherApi
    private val forecastLiveData = MutableLiveData<List<ForecastResponse.Sky>?>()

    fun getData(): LiveData<List<ForecastResponse.Sky>?> {
        return forecastLiveData
    }

    init {
        initApiService()
        loadWeather()
    }

    private fun initApiService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(WeatherApi::class.java)
    }

    private fun loadWeather() {
        apiService.getCurrentForecastData(cityId = CITY_ID, appId = APP_ID, units = UNITS)
            .enqueue(object : Callback<ForecastResponse> {
                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    val forecastList = response.body()?.list
                    forecastLiveData.value = forecastList
                }

                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    Log.e(TAG, t.message, t)
                }
            })
    }

}