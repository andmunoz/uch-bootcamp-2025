package cl.uchile.postgrado.mobile.pokedex.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.postgrado.mobile.pokedex.model.data.Pokemon
import cl.uchile.postgrado.mobile.pokedex.model.data.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {
    private val pokemonRepository = PokemonRepository()
    val pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    private var nextPage = 0

    init {
        Log.d("PokemonViewModel", "init called")
        getPokemon()
    }

    fun getNextPokemon() {
        nextPage++
        Log.d("PokemonViewModel", "getNextPokemon ${nextPage} called")
        getPokemon()
    }

    fun getPrevPokemon() {
        nextPage--
        Log.d("PokemonViewModel", "getPreviousPokemon ${nextPage} called")
        getPokemon()
    }

    fun getPokemon() {
        viewModelScope.launch {
            pokemonRepository.getPokemon(nextPage).collect {
                pokemonList.value = it
            }
        }
    }
}