package cl.uchile.postgrado.mobile.contactlist.model.room

import kotlinx.coroutines.flow.Flow

class ContactRepository(private val dao: ContactDao) {
    val contacts: Flow<List<Contact>> = dao.getAll()
    suspend fun addContact(contact: Contact) {
        dao.insert(contact)
    }
    suspend fun removeContact(contact: Contact) {
        dao.delete(contact)
    }
    suspend fun updateContact(contact: Contact) {
        dao.update(contact)
    }
}