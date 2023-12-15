package com.example.android_intern.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_intern.App
import com.example.android_intern.Characters
import com.example.android_intern.Episodes
import kotlinx.coroutines.launch

const val PREFIX = "https://rickandmortyapi.com/api/episode/"

class EpisodesViewModel : ViewModel() {
    companion object {
        var episode: String = ""
    }

    private val _liveData = MutableLiveData<Episodes>()
    val liveData: LiveData<Episodes>
        get() = _liveData

    init {
        getCallEpisodes()
    }

    private fun getCallEpisodes() {
        viewModelScope.launch {
            val characterList = App.apiService.getEpisode(episode)
            _liveData.value = characterList
        }
    }


}