package cl.uchile.postgrado.mobile.shoppinglist.model.room

import androidx.room.RoomDatabase
import cl.uchile.postgrado.mobile.shoppinglist.model.room.ProductDAO
import cl.uchile.postgrado.mobile.shoppinglist.model.room.ShoppingListDAO

abstract class AppDatabase : RoomDatabase() {
    abstract fun shoppingListDAO(): ShoppingListDAO
    abstract fun productDAO(): ProductDAO
}