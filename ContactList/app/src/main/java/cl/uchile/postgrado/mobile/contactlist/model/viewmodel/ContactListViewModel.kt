package cl.uchile.postgrado.mobile.contactlist.model.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.postgrado.mobile.contactlist.model.room.Contact
import cl.uchile.postgrado.mobile.contactlist.model.room.ContactRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ContactListViewModel(private val repository: ContactRepository): ViewModel() {
    val contacts: StateFlow<List<Contact>> =
        repository.contacts.stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )

    fun getContacts(): List<Contact>? {
        return contacts.value
    }
    fun addContact(contact: Contact, context: Context) {
        viewModelScope.launch {
            repository.addContact(contact)
        }
    }
}