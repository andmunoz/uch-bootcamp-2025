package cl.uchile.postgrado.mobile.contactlist.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.uchile.postgrado.mobile.contactlist.ui.screens.contactlist.ContactListScreen
import cl.uchile.postgrado.mobile.contactlist.ui.screens.contactnew.AddContactScreen

@Composable
fun ContactListNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "contact_list") {
        composable("contact_list") {
            ContactListScreen(navController)
        }
        composable("add_contact") {
            AddContactScreen(navController)
        }
        composable("edit_contact/{id}") {
            // EditContactScreen(id, navController)
        }
    }
}