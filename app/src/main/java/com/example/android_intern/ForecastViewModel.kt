package com.example.android_intern

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_intern.App.Companion.db
import kotlinx.coroutines.launch

private const val APP_ID = "eeba719e0ea1ed0d70d6ea433307695e"
private const val UNITS = "metric"
private const val CITY_ID = "622034"

class ForecastViewModel : ViewModel() {
    private val _liveData = MutableLiveData<List<ForecastResponse.Sky>>()
    private val forecastDao = db.forecastDao()
    val liveData: LiveData<List<ForecastResponse.Sky>>
        get() = _liveData

    init {
        loadWeather()
    }

    private fun loadWeather() {
        viewModelScope.launch {
            try {
                val forecasts = App.weatherApi.getCurrentForecastData(
                    cityId = CITY_ID,
                    appId = APP_ID,
                    units = UNITS
                )
                _liveData.value = forecasts.list.orEmpty()
                forecastDao.insertForecast(
                    liveData.value?.first()?.dtTxt.toString(),
                    liveData.value?.first()?.main?.temp!!,
                    liveData.value?.first()?.weather?.first()?.icon.toString()
                )
            } catch (e: Exception) {
                Log.e("Test", forecastDao.getAll().toString())
            }
        }
    }

    private fun convertToDataClass(response:ForecastResponse.Sky,dataClass:ForecastDataClass){
    }

}