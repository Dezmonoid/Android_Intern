package com.example.android_intern.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_intern.App
import com.example.android_intern.Characters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val PREFIX_PAGE = "https://rickandmortyapi.com/api/character?page="

class CharactersViewModel : ViewModel() {
    private val _liveData = MutableLiveData<Characters>()
    val liveData: LiveData<Characters>
        get() = _liveData
    private var nextPage = ""

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val characterList = App.apiService.getCharacters()
            withContext(Dispatchers.Main) {
                _liveData.value = characterList
            }

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