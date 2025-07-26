package cl.uchile.postgrado.mobile.indicadores.ui.screens.indexDetailScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import cl.uchile.postgrado.mobile.indicadores.ui.components.IndexTopBar
import java.util.Date

@Composable
fun IndexDetailScreen(navController: NavHostController, index: String?, date: String?) {
    Scaffold(
        topBar = {
            IndexTopBar()
        },
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        IndexDetailForm(navController, innerPadding)
    }
}