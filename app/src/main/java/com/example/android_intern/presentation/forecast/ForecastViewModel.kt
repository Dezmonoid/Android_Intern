package com.example.android_intern.presentation.forecast

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.android_intern.domain.ForecastRepository
import com.example.android_intern.presentation.model.ForecastUI
import com.example.android_intern.presentation.toUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val TAG = "ForecastViewModel"

class ForecastViewModel(
    private val repository: ForecastRepository
) : ViewModel() {
    private val _liveData = MutableLiveData<List<ForecastUI>>()
    val liveData: LiveData<List<ForecastUI>>
        get() = _liveData

    init {
        loadWeather()
    }

    private fun loadWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val forecast = repository.getForecast().map { it.toUI() }
                withContext(Dispatchers.Main) {
                    _liveData.value = forecast
                }
            } catch (e: Throwable) {
                Log.e(TAG, e.message, e)
            }
        }
    }
}

class ForecastViewModelFactory(
    private val repository: ForecastRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ForecastViewModel(repository) as T
    }
}