package cl.uchile.postgrado.mobile.contactlist.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    var phoneNumber: String,
    var email: String
)
