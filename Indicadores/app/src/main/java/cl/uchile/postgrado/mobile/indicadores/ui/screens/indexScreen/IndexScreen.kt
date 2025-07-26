package cl.uchile.postgrado.mobile.indicadores.ui.screens.indexScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import cl.uchile.postgrado.mobile.indicadores.ui.components.IndexTopBar

@Composable
fun IndexScreen(navController: NavHostController) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            IndexTopBar()
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        IndexForm(navController, snackbarHostState, innerPadding)
    }
}