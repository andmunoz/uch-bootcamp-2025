package cl.uchile.postgrado.mobile.shoppinglist

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.uchile.postgrado.mobile.shoppinglist.model.UserSettingsViewModel
import cl.uchile.postgrado.mobile.shoppinglist.ui.screens.addProductScreen.AddProductScreen
import cl.uchile.postgrado.mobile.shoppinglist.ui.screens.productDetailScreen.ProductDetailScreen
import cl.uchile.postgrado.mobile.shoppinglist.ui.screens.shoppingListScreen.ShoppingListScreen
import cl.uchile.postgrado.mobile.shoppinglist.ui.screens.shoppingListScreen.ShoppingListViewModel
import cl.uchile.postgrado.mobile.shoppinglist.ui.theme.DefaultTheme


class MainActivity : ComponentActivity() {
    companion object {
        lateinit var userSettingsViewModel: UserSettingsViewModel
        lateinit var shoppingListViewModel: ShoppingListViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { false }

        // Cargar los ajustes del usuario
        userSettingsViewModel = UserSettingsViewModel()
        userSettingsViewModel.getSettings(applicationContext)

        // Cargar la lista de compras ya guardada
        shoppingListViewModel = ShoppingListViewModel()
        shoppingListViewModel.loadProducts(applicationContext)

        enableEdgeToEdge()
        setContent {
            DefaultTheme(userSettingsViewModel.theme) {
                AppNavigation()
            }
        }
    }

    // Solo se ejecuta al CERRAR la aplicación
    override fun onDestroy() {
        super.onDestroy()

        // Guardar los ajustes del usuario
        userSettingsViewModel.saveSettings(applicationContext)

        // Guardar la lista de compras existente en la app
        shoppingListViewModel.saveProducts(applicationContext)
    }
}

// Componente que permite navegar entre pantallas
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "product_list") {
        composable("product_list") {
            ShoppingListScreen(navController)
        }
        composable("product_detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            ProductDetailScreen(id, navController)
        }
        composable("add_product") { backStackEntry ->
            AddProductScreen(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingListPreview() {
    AppNavigation()
}