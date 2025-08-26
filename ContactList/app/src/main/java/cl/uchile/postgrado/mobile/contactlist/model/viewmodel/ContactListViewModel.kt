package cl.uchile.postgrado.mobile.contactlist.model.viewmodel

import android.content.Context
import android.util.Log
import androidx.room.Room
import cl.uchile.postgrado.mobile.contactlist.model.room.Contact
import cl.uchile.postgrado.mobile.contactlist.model.room.ContactDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactListViewModel {
    private val contacts = mutableListOf<Contact>()
    object DataBaseBuilder {
        private var db: ContactDatabase? = null
        fun getInstance(context: Context): ContactDatabase {
            if (db == null) {
                db = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    ContactDatabase.DBNAME
                ).build()
            }
            return db!!
        }
    }
    fun loadContacts(context: Context) {
        val db = DataBaseBuilder.getInstance(context)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("ContactListViewModel", "Loading contacts")
            contacts.addAll(db.contactDao().getAll())
        }
    }
    fun getContacts(): List<Contact> {
        return contacts
    }
    fun addContact(contact: Contact, context: Context) {
        contacts.add(contact)
        val db = DataBaseBuilder.getInstance(context)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("ContactListViewModel", "Adding contact: $contact")
            db.contactDao().insert(contact)
        }
    }
}