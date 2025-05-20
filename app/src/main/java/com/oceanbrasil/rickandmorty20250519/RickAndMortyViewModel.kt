package com.oceanbrasil.rickandmorty20250519

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RickAndMortyViewModel : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(RickAndMortyApi::class.java)

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        viewModelScope.launch {
            val response = service.getCharacters()
            _characters.value = response.results
        }

    }
}