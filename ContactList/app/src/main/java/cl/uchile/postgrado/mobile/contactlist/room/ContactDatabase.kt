package cl.uchile.postgrado.mobile.contactlist.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase: RoomDatabase() {
    companion object {
        const val DBNAME = "contacts.db"
        @Volatile private var db: ContactDatabase? = null
        fun getDatabase(context: Context): ContactDatabase {
            if (db == null) {
                db = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    DBNAME
                ).build()
            }
            return db!!
        }
    }
    abstract fun contactDao(): ContactDao
}