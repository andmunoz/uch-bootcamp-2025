package cl.uchile.postgrado.mobile.contactlist.ui.screens.contactlist

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cl.uchile.postgrado.mobile.contactlist.room.ContactDatabase
import cl.uchile.postgrado.mobile.contactlist.model.ContactRepository
import cl.uchile.postgrado.mobile.contactlist.model.ContactListViewModel
import cl.uchile.postgrado.mobile.contactlist.services.ContactApiService

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun ContactListComponent(modifier: Modifier = Modifier, navController: NavController) {
    val db = remember { ContactDatabase.getDatabase(navController.context) }
    val api = remember { ContactApiService.RetrofitInstance.api }
    val repository = remember { ContactRepository(db.contactDao(), api) }
    /* val contactListViewModel: ContactListViewModel = viewModel(
        factory = ContactListViewModelFactory(repository)
    ) */
    val contactListViewModel = ContactListViewModel(navController.context)
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