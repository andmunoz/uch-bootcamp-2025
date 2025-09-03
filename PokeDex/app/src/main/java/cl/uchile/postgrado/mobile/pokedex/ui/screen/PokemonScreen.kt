package cl.uchile.postgrado.mobile.pokedex.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PokemonScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        PokemonList(modifier = Modifier.padding(innerPadding))
    }
}
