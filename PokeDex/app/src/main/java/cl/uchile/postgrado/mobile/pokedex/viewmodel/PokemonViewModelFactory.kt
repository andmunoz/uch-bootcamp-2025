package cl.uchile.postgrado.mobile.pokedex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.uchile.postgrado.mobile.pokedex.model.data.PokemonRepository

class PokemonViewModelFactory(): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            return PokemonViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}