package cl.uchile.postgrado.mobile.shoppinglist.model.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cl.uchile.postgrado.mobile.shoppinglist.model.room.ProductData

@Dao
interface ProductDAO {
    @Insert
    suspend fun insert(product: ProductData): Long

    @Query("SELECT * FROM products WHERE shoppingListId = :shoppingListId")
    suspend fun getProducts(shoppingListId: Int): List<ProductData>

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProductById(id: Int): ProductData?

    @Query("SELECT * FROM products WHERE shoppingListId = :shoppingListId AND productCategory = :category")
    suspend fun getProductsByCategory(shoppingListId: Int, category: String): List<ProductData>

    @Update
    suspend fun updateProduct(product: ProductData): Long

    @Delete
    suspend fun removeProduct(product: ProductData): Long
}