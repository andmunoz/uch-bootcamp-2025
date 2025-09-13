package cl.uchile.postgrado.mobile.catalogo.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.uchile.postgrado.mobile.catalogo.ui.addproduct.AddProductScreen
import cl.uchile.postgrado.mobile.catalogo.ui.catalog.CatalogScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "catalog") {
        composable("catalog") {
            CatalogScreen(navController)
        }
        composable("add_product") {
            AddProductScreen(navController)
        }
    }
}