package com.example.android_intern.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_intern.App
import com.example.android_intern.CharacterInterface
import com.example.android_intern.Characters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val PREFIX_PAGE = "https://rickandmortyapi.com/api/character?page="

class CharactersViewModel : ViewModel() {
    private val _liveData = MutableLiveData<List<CharacterInterface>>()
    val liveData: LiveData<List<CharacterInterface>>
        get() = _liveData
    private var nextPage = ""
    private var content: MutableList<CharacterInterface> =
        emptyList<CharacterInterface>().toMutableList()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val characterList = App.apiService.getCharacters()
            withContext(Dispatchers.Main) {
                content = characterList.results?.map { it.toCharacter() }?.toMutableList()
                    ?: emptyList<CharacterInterface>().toMutableList()
                content.add(CharacterInterface.Button)
                _liveData.value = content.toList()
            }
            nextPage = characterList.info?.next ?: ""
        }
    }

    fun nextPage() {
        viewModelScope.launch {
            val characterList = App.apiService.getCharactersPage(nextPage.removePrefix(PREFIX_PAGE))
            content.forEach {
                if (it == CharacterInterface.Button) {
                    content.remove(it)
                }
            }
            content.addAll(
                characterList.results?.map { it.toCharacter() }?.toMutableList()
                    ?: emptyList<CharacterInterface>().toMutableList()
            )
            if (characterList.info?.next != null) {
                content.add(CharacterInterface.Button)
                nextPage = characterList.info.next
            }
            _liveData.value = content.toList()
        }
    }
}

private fun Characters.Result?.toCharacter(): CharacterInterface.Character =
    CharacterInterface.Character(
        typeCharacter = this?.type,
        genderCharacter = this?.gender,
        image = this?.image,
        episode = this?.episode as List<String>,
        id = this.id
    )
