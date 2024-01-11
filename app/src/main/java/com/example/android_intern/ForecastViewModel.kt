package com.example.android_intern

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
    private val _liveData = MutableLiveData<List<ForecastDataClass>>()
    private val forecastDao = db.forecastDao()
    val liveData: LiveData<List<ForecastDataClass>>
        get() = _liveData

    init {
        loadWeather()
    }

    private fun loadWeather() {
        viewModelScope.launch {
            try {
                val response = App.weatherApi.getCurrentForecastData(
                    cityId = CITY_ID,
                    appId = APP_ID,
                    units = UNITS
                )
                _liveData.value = response.list?.map { it.toForecastDao() }
                forecastDao.deleteAll()
                liveData.value?.map {
                    forecastDao.insertForecast(
                        it.dtTxt.toString(),
                        it.temp ?: 0.0, it.icon.toString()
                    )
                }
            } catch (e: Exception) {
                _liveData.value = forecastDao.getAll()
            }
        }
    }

    private fun ForecastResponse.Sky.toForecastDao(): ForecastDataClass =
        ForecastDataClass(
            id = null,
            dtTxt = this.dtTxt,
            temp = this.main?.temp,
            icon = this.weather?.first()?.icon
        )
}