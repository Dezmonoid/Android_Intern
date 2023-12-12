package com.example.android_intern.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_intern.App
import com.example.android_intern.Characters
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {
    private val _liveData = MutableLiveData<Characters>()
    val liveData: LiveData<Characters>
        get() = _liveData

    init {
        getCalledCharacters()
    }

    private fun getCalledCharacters() {
        viewModelScope.launch {
            val characterList = App.apiService.getCharacters()
            _liveData.value = characterList
        }
    }
}