package cl.uchile.postgrado.mobile.contactlist.ui.screens.contactlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cl.uchile.postgrado.mobile.contactlist.model.room.ContactDatabase
import cl.uchile.postgrado.mobile.contactlist.model.room.ContactRepository
import cl.uchile.postgrado.mobile.contactlist.model.viewmodel.ContactListViewModel

@Composable
fun ContactListComponent(modifier: Modifier = Modifier, navController: NavController) {
    val db = remember { ContactDatabase.getDatabase(navController.context) }
    val repository = remember { ContactRepository(db.contactDao()) }
    val contactListViewModel: ContactListViewModel = viewModel(
        factory = ContactListViewModelFactory(repository)
    )
    val contacts by contactListViewModel.contacts.collectAsState()
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