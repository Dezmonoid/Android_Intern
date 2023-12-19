package com.example.android_intern.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_intern.App
import com.example.android_intern.Episode
import kotlinx.coroutines.launch

const val PREFIX = "https://rickandmortyapi.com/api/episode/"

class EpisodesViewModel : ViewModel() {
    companion object {
        var episode: String = ""
    }

    private val _liveData = MutableLiveData<List<Episode>>()
    val liveData: LiveData<List<Episode>>
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