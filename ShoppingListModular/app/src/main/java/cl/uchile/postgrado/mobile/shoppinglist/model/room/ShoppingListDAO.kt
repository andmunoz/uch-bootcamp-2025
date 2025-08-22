package cl.uchile.postgrado.mobile.shoppinglist.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cl.uchile.postgrado.mobile.shoppinglist.model.room.ShoppingListData

@Dao
interface ShoppingListDAO {
    @Insert
    suspend fun createList(list: ShoppingListData): Long

    @Update
    suspend fun updateList(list: ShoppingListData): Long

    @Query("SELECT * FROM shopping_lists")
    suspend fun getShoppingLists(): List<ShoppingListData>
}