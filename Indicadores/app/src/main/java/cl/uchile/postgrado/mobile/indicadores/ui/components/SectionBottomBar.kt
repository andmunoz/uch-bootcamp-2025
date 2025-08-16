package cl.uchile.postgrado.mobile.indicadores.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController

enum class Destination(
    val route: String,
    val icon: ImageVector,
    val contentDescription: String,
    val label: String
) {
    NAC("nacional", icon = Icons.Filled.Favorite, contentDescription = "Nacionales", label = "Nacional"),
    INT("internacional", icon = Icons.Filled.FavoriteBorder, contentDescription = "Internacionales", label = "Internacional")
}

@Composable
fun SectionBottomBar(navController: NavHostController, actualDestination: Destination){
    var selectedDestination by rememberSaveable { mutableIntStateOf(actualDestination.ordinal) }
    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets){
        Destination.entries.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = selectedDestination == index,
                onClick = {
                    navController.navigate(destination.route)
                    selectedDestination = index
                },
                icon = {
                    Icon(
                        destination.icon,
                        contentDescription = destination.contentDescription
                    )
                },
                label = {
                    Text(destination.label)
                }
            )
        }
    }
}