package com.example.android_intern.presentation.forecast

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_intern.domain.ForecastRepository
import com.example.android_intern.domain.model.ForecastType
import com.example.android_intern.presentation.model.ForecastUI
import com.example.android_intern.presentation.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val TAG = "ForecastViewModel"

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val repository: ForecastRepository
) : ViewModel() {
    private val _liveData = MutableLiveData<List<ForecastUI>>()
    private val _event = MutableLiveData<Event<ForecastType>>()
    private val _region = MutableLiveData<String>()
    val liveData: LiveData<List<ForecastUI>>
        get() = _liveData
    val event: LiveData<Event<ForecastType>>
        get() = _event
    val region: LiveData<String>
        get() = _region

    private fun loadWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val forecast = repository.getForecast()
                withContext(Dispatchers.Main) {
                    _liveData.value = forecast.forecast.map { it.toUI() }
                    _region.value = forecast.region
                    _event.value = Event(forecast.forecastType)
                }
            } catch (e: Throwable) {
                Log.e(TAG, e.message, e)
            }
        }
    }

    fun getRegion() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getRegion()
                loadWeather()
            } catch (e: Throwable) {
                Log.e(TAG, e.message, e)
            }
        }
    }
}