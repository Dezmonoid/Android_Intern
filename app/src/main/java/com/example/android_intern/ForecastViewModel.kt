package com.example.android_intern

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastViewModel : ViewModel() {
    private val _liveData = MutableLiveData<List<ForecastResponse.Sky>>()
    val liveData: LiveData<List<ForecastResponse.Sky>>
        get() = _liveData

    init {
        loadWeather()
    }

    private fun loadWeather() {
        App.weatherApi.getCurrentForecastData(cityId = CITY_ID, appId = APP_ID, units = UNITS)
            .enqueue(object : Callback<ForecastResponse> {
                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    val forecastList = response.body()?.list.orEmpty()
                    _liveData.value = forecastList
                }

                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    Log.e(TAG, t.message, t)
                }
            })
    }
}