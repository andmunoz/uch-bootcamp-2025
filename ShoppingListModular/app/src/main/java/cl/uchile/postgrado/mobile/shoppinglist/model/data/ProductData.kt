package cl.uchile.postgrado.mobile.shoppinglist.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductData(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val productQuantity: Int,
    val productName: String,
    val productBrand: String,
    val productDescription: String,
    val productCategory: String,
    val productPrice: Float?,
    var shoppingListId: Int?
)