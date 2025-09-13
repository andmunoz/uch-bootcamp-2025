package cl.uchile.postgrado.mobile.contactlist.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.postgrado.mobile.contactlist.room.Contact
import cl.uchile.postgrado.mobile.contactlist.model.ContactRepository
import cl.uchile.postgrado.mobile.contactlist.room.ContactDatabase
import cl.uchile.postgrado.mobile.contactlist.services.ContactApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ContactListViewModel(private val context: Context): ViewModel() {
    private var _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts: StateFlow<List<Contact>> = _contacts

    init {
        getContacts()
    }

    fun getContacts(): List<Contact>? {
        viewModelScope.launch {
            try {
                // val response = ContactApiService.RetrofitInstance.api.getContacts()
                val db = ContactDatabase.getDatabase(context)
                _contacts = db.contactDao().getAll() as MutableStateFlow<List<Contact>>
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return _contacts.value
    }

    fun addContact(contact: Contact, context: Context) {
        try {
            val db = ContactDatabase.getDatabase(context)
            viewModelScope.launch {
                db.contactDao().insert(contact)
            }
            /* viewModelScope.launch {
                repository.addContact(contact)
            } */
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}