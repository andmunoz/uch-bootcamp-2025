package cl.uchile.postgrado.mobile.shoppinglist.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import cl.uchile.postgrado.mobile.shoppinglist.R

// Componente que muestra la barra de navegación
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListTopBar(drawerState: DrawerState, scope: CoroutineScope) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier.padding(start = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )

    )
}
