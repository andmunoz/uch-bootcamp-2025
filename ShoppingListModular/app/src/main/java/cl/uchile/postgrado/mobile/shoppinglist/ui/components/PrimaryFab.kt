package cl.uchile.postgrado.mobile.shoppinglist.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cl.uchile.postgrado.mobile.shoppinglist.R

@Composable
fun PrimaryFab(text: String, onClick: () -> Unit, navController: NavHostController) {
    ExtendedFloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
        onClick = { navController.navigate("add_product") }
    ) {
        Icon(
            imageVector = Icons.Sharp.Add,
            contentDescription = stringResource(R.string.add_button),
            tint = Color.Yellow
        )
        Text(
            text = stringResource(R.string.add_button),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}