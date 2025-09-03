package cl.uchile.postgrado.mobile.pokedex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.postgrado.mobile.pokedex.model.data.Pokemon
import cl.uchile.postgrado.mobile.pokedex.model.data.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {
    private val pokemonRepository = PokemonRepository()
    val pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())

    init {
        viewModelScope.launch {
            pokemonRepository.getPokemon().collect {
                pokemonList.value = it
            }
        }
    }
}