package cl.uchile.postgrado.mobile.shoppinglist.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_lists")
data class ShoppingListData(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val listName: String,
    val listDescription: String,
    val listCategory: String
)