package cl.uchile.postgrado.mobile.pokedex.ui.screen

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
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
    val listState = rememberLazyListState()

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { index ->
                Log.d("PokemonList", "Index: ${(index?:0)+1}, Size: ${pokemonList.size}")
                if ((index?:0)+1 == pokemonList.size) {
                    pokemonListViewModel.getNextPokemon()
                }
            }
    }

    LazyColumn(
        state = listState,
        modifier = modifier
    ) {
        items(pokemonList.size) { index ->
            PokemonCard(pokemon = pokemonList[index])
        }
    }
}