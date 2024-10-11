package com.ded

object CharacterRepository {
    private var savedCharacter: Character? = null

    fun saveCharacter(character: Character) {
        savedCharacter = character
    }

    fun getCharacter(): Character? {
        return savedCharacter
    }
}