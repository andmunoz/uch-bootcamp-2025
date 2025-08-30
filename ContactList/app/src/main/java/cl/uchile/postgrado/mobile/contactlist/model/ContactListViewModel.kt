package cl.uchile.postgrado.mobile.contactlist.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.postgrado.mobile.contactlist.room.Contact
import cl.uchile.postgrado.mobile.contactlist.model.ContactRepository
import cl.uchile.postgrado.mobile.contactlist.services.ContactApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ContactListViewModel(private val repository: ContactRepository): ViewModel() {
    private var _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> = _contacts

    init {
        viewModelScope.launch {
            try {
                val response = ContactApiService.RetrofitInstance.api.getContacts()
                _contacts.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getContacts(): List<Contact>? {
        return contacts.value
    }
    fun addContact(contact: Contact, context: Context) {
        viewModelScope.launch {
            repository.addContact(contact)
        }
    }
}