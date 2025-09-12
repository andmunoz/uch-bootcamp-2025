package cl.uchile.postgrado.mobile.shoppinglist.ui.screens.shoppingListScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cl.uchile.postgrado.mobile.shoppinglist.MainActivity
import cl.uchile.postgrado.mobile.shoppinglist.R
import cl.uchile.postgrado.mobile.shoppinglist.ui.components.PrimaryFab
import cl.uchile.postgrado.mobile.shoppinglist.ui.components.ShoppingListTopBar
import kotlinx.coroutines.CoroutineScope

// Componente que muestra la pantalla principal
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            ShoppingListTopBar(stringResource(R.string.app_name))
        },
        floatingActionButton = {
            PrimaryFab(stringResource(R.string.add_button),
                       { navController.navigate("add_product") }, navController)
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        val shoppingListViewModel
        ShoppingListForm(
            innerPadding = Modifier.padding(paddingValues = innerPadding),
            navController = navController,
            productListModel = shoppingListViewModel
        )
    }
}
