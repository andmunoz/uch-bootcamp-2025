package cl.uchile.postgrado.mobile.contactlist.ui.screens.contactnew

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cl.uchile.postgrado.mobile.contactlist.R
import cl.uchile.postgrado.mobile.contactlist.room.Contact
import cl.uchile.postgrado.mobile.contactlist.room.ContactDatabase
import cl.uchile.postgrado.mobile.contactlist.model.ContactRepository
import cl.uchile.postgrado.mobile.contactlist.model.ContactListViewModel
import cl.uchile.postgrado.mobile.contactlist.services.ContactApiService

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AddContactForm(modifier: Modifier = Modifier, navController: NavController) {
    val db = remember { ContactDatabase.getDatabase(navController.context) }
    val api = remember { ContactApiService.RetrofitInstance.api }
    val repository = remember { ContactRepository(db.contactDao(), api) }
    /* val contactListViewModel: ContactListViewModel = viewModel(
        factory = ContactListViewModelFactory(repository)
    ) */
    val contactListViewModel = ContactListViewModel(navController.context)
    var contactName by remember { mutableStateOf("") }
    var contactPhoneNumber by remember { mutableStateOf("") }
    var contactEmail by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        TextField(
            value = contactName,
            onValueChange = { contactName = it },
            label = {
                Text(stringResource(R.string.name_field))
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        TextField(
            value = contactPhoneNumber,
            onValueChange = { contactPhoneNumber = it },
            label = {
                Text(stringResource(R.string.phone_field))
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        TextField(
            value = contactEmail,
            onValueChange = { contactEmail = it },
            label = {
                Text(stringResource(R.string.email_field))
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        Button(
            onClick = {
                contactListViewModel.addContact(
                    Contact(
                        name = contactName,
                        phoneNumber = contactPhoneNumber,
                        email = contactEmail
                    ),
                    navController.context
                )
                navController.popBackStack()
            },
            modifier = Modifier
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = stringResource(R.string.add_contact),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}