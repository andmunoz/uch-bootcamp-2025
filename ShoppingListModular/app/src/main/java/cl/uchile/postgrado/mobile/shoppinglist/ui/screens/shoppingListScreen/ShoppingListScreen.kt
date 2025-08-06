package cl.uchile.postgrado.mobile.shoppinglist.ui.screens.shoppingListScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cl.uchile.postgrado.mobile.shoppinglist.R
import cl.uchile.postgrado.mobile.shoppinglist.ui.components.ShoppingListTopBar
import kotlinx.coroutines.CoroutineScope

// Componente que muestra el botón flotante de la pantalla principal
@Composable
fun AddProductFloatingActionButton(navController: NavHostController) {

}

// Componente que muestra la pantalla principal
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(navController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope: CoroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            ShoppingListTopBar(drawerState, scope)
        },
        floatingActionButton = {
            AddProductFloatingActionButton(navController)
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        ShoppingListForm(
            modifier = Modifier.padding(innerPadding),
            navController
        )
    }
}
