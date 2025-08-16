package cl.uchile.postgrado.mobile.indicadores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.uchile.postgrado.mobile.indicadores.ui.components.Destination
import cl.uchile.postgrado.mobile.indicadores.ui.screens.indexDetailScreen.IndexDetailScreen
import cl.uchile.postgrado.mobile.indicadores.ui.screens.indexScreen.IndexScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "nacional") {
        composable("nacional") {
            IndexScreen(navController, Destination.NAC)
        }
        composable("internacional") {
            IndexScreen(navController, Destination.INT)
        }
        composable("index_detail/{index}/{dd}/{mm}/{yyyy}") { backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")
            val dateDay = backStackEntry.arguments?.getString("dd")
            val dateMonth = backStackEntry.arguments?.getString("mm")
            val dateYear = backStackEntry.arguments?.getString("yyyy")

            IndexDetailScreen(navController, index, "$dateDay/$dateMonth/$dateYear")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IndexesPreview() {
    AppNavigation()
}