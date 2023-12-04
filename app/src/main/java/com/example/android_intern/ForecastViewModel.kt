package com.example.android_intern

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val APP_ID = "eeba719e0ea1ed0d70d6ea433307695e"
private const val UNITS = "metric"
private const val CITY_ID = "622034"
private const val TAG = "Debug"

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
                    val forecasts = response.body()?.list.orEmpty()
                    _liveData.value = forecasts
                }

                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    Log.e(TAG, t.message, t)
                }
            })
    }
}