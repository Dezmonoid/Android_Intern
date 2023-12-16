package com.example.android_intern

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

private const val APP_ID = "eeba719e0ea1ed0d70d6ea433307695e"
private const val UNITS = "metric"
private const val CITY_ID = "622034"

class ForecastViewModel : ViewModel() {
    private val _liveData = MutableLiveData<List<ForecastResponse.Sky>>()
    val liveData: LiveData<List<ForecastResponse.Sky>>
        get() = _liveData

    init {
        loadWeather()
    }

    private fun loadWeather() {
        viewModelScope.launch {
            val forecasts = App.weatherApi.getCurrentForecastData(
                cityId = CITY_ID,
                appId = APP_ID,
                units = UNITS
            ).execute().body()?.list.orEmpty()
            _liveData.value = forecasts
        }
    }
}