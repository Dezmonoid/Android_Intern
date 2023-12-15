package com.example.android_intern.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_intern.App
import com.example.android_intern.Characters
import kotlinx.coroutines.launch

const val PREFIX_PAGE = "https://rickandmortyapi.com/api/character?page="

class CharactersViewModel : ViewModel() {
    private val _liveData = MutableLiveData<Characters>()
    private var nextPage = ""
    val liveData: LiveData<Characters>
        get() = _liveData

    init {
        getCalledCharacters()
    }

    private fun getCalledCharacters() {
        viewModelScope.launch {
            val characterList = App.apiService.getCharacters()
            _liveData.value = characterList
            nextPage = characterList.info?.next ?: ""
        }
    }

    fun nextPage() {
        viewModelScope.launch {
            val characterList = App.apiService.getCharactersPage(
                nextPage.removePrefix(PREFIX_PAGE)
            )
            _liveData.value = characterList
            nextPage = characterList.info?.next.toString()
        }
    }
}