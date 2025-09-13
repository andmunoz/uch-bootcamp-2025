package cl.uchile.postgrado.mobile.catalogo.ui.catalog

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun CatalogScreen(navController: NavController) {
    Scaffold(
        floatingActionButton = {
            AddProductButton {
                navController.navigate("add_product")
            }
        }
    )
    { padding ->
        CatalogComponent(
            modifier = Modifier.padding(padding),
            navController = navController)
    }
}
