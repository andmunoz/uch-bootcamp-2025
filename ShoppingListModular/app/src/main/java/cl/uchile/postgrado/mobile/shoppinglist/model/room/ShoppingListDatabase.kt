package cl.uchile.postgrado.mobile.shoppinglist.model.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class ShoppingListDatabase : RoomDatabase() {
    companion object {
        const val DBNAME = "shopping_list_db"
        @Volatile private var db: ShoppingListDatabase? = null
        fun getDatabase(context: Context): ShoppingListDatabase {
            if (db == null) {
                synchronized(ShoppingListDatabase::class.java) {
                    if (db == null) {
                        db = Room.databaseBuilder(
                            context.applicationContext,
                            ShoppingListDatabase::class.java,
                            DBNAME
                        ).build()
                    }
                }
            }
            return db!!
        }
    }
    abstract fun shoppingListDAO(): ShoppingListDAO
    abstract fun productDAO(): ProductDAO
}