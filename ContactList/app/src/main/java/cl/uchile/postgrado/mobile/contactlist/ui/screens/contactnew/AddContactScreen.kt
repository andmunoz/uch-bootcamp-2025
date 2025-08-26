package cl.uchile.postgrado.mobile.contactlist.ui.screens.contactnew

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun AddContactScreen(navController: NavController) {
    Scaffold(
        topBar = {
            AddContactTopBar(navController)
        }
    ) { padding ->
        AddContactForm(modifier = Modifier.padding(padding), navController)
    }
}