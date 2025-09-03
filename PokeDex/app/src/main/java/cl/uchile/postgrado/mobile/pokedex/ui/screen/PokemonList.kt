package cl.uchile.postgrado.mobile.pokedex.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.uchile.postgrado.mobile.pokedex.viewmodel.PokemonViewModel
import cl.uchile.postgrado.mobile.pokedex.viewmodel.PokemonViewModelFactory

@Composable
fun PokemonList(modifier: Modifier = Modifier) {
    val pokemonListViewModel: PokemonViewModel = viewModel(
        factory = PokemonViewModelFactory()
    )
    val pokemonList by pokemonListViewModel.pokemonList.collectAsState()

    LazyColumn(modifier = modifier) {
        items(pokemonList.size) { index ->
            PokemonCard(pokemon = pokemonList[index])
        }
    }
}