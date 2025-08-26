package cl.uchile.postgrado.mobile.contactlist.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase: RoomDatabase() {
    companion object {
        const val DBNAME = "contacts.db"
    }
    abstract fun contactDao(): ContactDao
}