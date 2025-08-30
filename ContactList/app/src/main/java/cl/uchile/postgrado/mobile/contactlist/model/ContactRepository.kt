package cl.uchile.postgrado.mobile.contactlist.model

import cl.uchile.postgrado.mobile.contactlist.room.Contact
import cl.uchile.postgrado.mobile.contactlist.room.ContactDao
import cl.uchile.postgrado.mobile.contactlist.services.ContactApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ContactRepository(private val dao: ContactDao, private val apiService: ContactApiService) {
    val contacts: Flow<List<Contact>> = dao.getAll()

    // Funciones que solo interactúan con el ROOM
    suspend fun addContact(contact: Contact) {
        dao.insert(contact)
    }
    suspend fun removeContact(contact: Contact) {
        dao.delete(contact)
    }
    suspend fun updateContact(contact: Contact) {
        dao.update(contact)
    }

    // Funciones que solo interactúan con la API
    fun fetchContacts(): Flow<List<Contact>> = flow {
        val contacts = apiService.getContacts()
        emit(contacts)
    }

    fun pushContact(contact: Contact): Flow<Contact> = flow {
        val newContact = apiService.addContact(contact)
        emit(newContact)
    }

    fun pushContacts(contacts: List<Contact>): Flow<List<Contact>> = flow {
        for (contact in contacts) {
            apiService.addContact(contact)
        }
        emit(contacts)
    }
}