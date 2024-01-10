package com.example.android_intern

sealed class CharacterInterface {
    data class Character(
        val typeCharacter: String?,
        val genderCharacter: String?,
        val image: String?,
        val episode: List<String>,
        val id: Int?,
    ) :
        CharacterInterface()

    object Button : CharacterInterface()
}