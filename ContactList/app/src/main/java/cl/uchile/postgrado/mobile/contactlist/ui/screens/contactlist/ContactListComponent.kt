package cl.uchile.postgrado.mobile.contactlist.ui.screens.contactlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cl.uchile.postgrado.mobile.contactlist.model.viewmodel.ContactListViewModel
import kotlinx.coroutines.coroutineScope

@Composable
fun ContactListComponent(modifier: Modifier = Modifier, navController: NavController) {
    val contacts = ContactListViewModel().getContacts()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(contacts.size) { index ->
            val contact = contacts[index]
            ContactItemComponent(contact = contact, navController)
        }
    }
}