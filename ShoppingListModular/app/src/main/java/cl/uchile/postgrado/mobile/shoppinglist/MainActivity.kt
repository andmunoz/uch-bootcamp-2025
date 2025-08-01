package cl.uchile.postgrado.mobile.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.uchile.postgrado.mobile.shoppinglist.ui.screens.addProductScreen.AddProductScreen
import cl.uchile.postgrado.mobile.shoppinglist.ui.screens.productDetailScreen.ProductDetailScreen
import cl.uchile.postgrado.mobile.shoppinglist.ui.screens.shoppingListScreen.ShoppingListScreen
import cl.uchile.postgrado.mobile.shoppinglist.ui.theme.DefaultTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DefaultTheme {
                AppNavigation()
            }
        }
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