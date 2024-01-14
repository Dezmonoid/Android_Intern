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
        var episode: List<String> = listOf()
    }

    private val _liveData = MutableLiveData<List<Episode>>()
    val liveData: LiveData<List<Episode>>
        get() = _liveData

    init {
        getCallEpisodes()
    }

    private fun getCallEpisodes() {
        viewModelScope.launch {
            if (episode.size == 1) {
                val characterList = App.apiService.getOneEpisode(episode.joinToString())
                _liveData.value = listOf(characterList)
            } else {
                val characterList = App.apiService.getEpisode(episode.joinToString())
                _liveData.value = characterList
            }
        }
    }
}