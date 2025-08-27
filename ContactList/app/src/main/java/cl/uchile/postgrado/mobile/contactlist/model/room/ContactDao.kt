package cl.uchile.postgrado.mobile.contactlist.model.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts")
    fun getAll(): Flow<List<Contact>>
    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getById(id: Int): Flow<Contact?>
    @Query("SELECT * FROM contacts WHERE name LIKE :name")
    fun getByName(name: String): Flow<List<Contact>>
    @Insert
    suspend fun insert(contact: Contact)
    @Update
    suspend fun update(contact: Contact)
    @Delete
    suspend fun delete(contact: Contact)
}