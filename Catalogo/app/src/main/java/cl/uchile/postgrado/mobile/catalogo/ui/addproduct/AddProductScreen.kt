package cl.uchile.postgrado.mobile.catalogo.ui.addproduct

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun AddProductScreen(navController: NavController) {
    Scaffold { padding ->
        AddProductForm(modifier = Modifier.padding(padding), navController)
    }
}