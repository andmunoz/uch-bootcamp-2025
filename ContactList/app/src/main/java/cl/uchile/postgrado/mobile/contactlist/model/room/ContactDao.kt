package cl.uchile.postgrado.mobile.contactlist.model.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts")
    fun getAll(): List<Contact>
    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getById(id: Int): Contact?
    @Query("SELECT * FROM contacts WHERE name LIKE :name")
    fun getByName(name: String): List<Contact>
    @Insert
    fun insert(contact: Contact)
    @Update
    fun update(contact: Contact)
    @Delete
    fun delete(contact: Contact)
}