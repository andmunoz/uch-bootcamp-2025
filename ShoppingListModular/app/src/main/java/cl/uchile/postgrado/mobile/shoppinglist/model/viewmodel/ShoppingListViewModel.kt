package cl.uchile.postgrado.mobile.shoppinglist.model.viewmodel

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.room.Room
import cl.uchile.postgrado.mobile.shoppinglist.model.room.AppDatabase
import cl.uchile.postgrado.mobile.shoppinglist.model.room.ProductData
import cl.uchile.postgrado.mobile.shoppinglist.model.room.ShoppingListData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingListViewModel : ViewModel() {
    var products = mutableListOf<ProductData>()
        private set
    var shoppingLists = mutableListOf<ShoppingListData>()
        private set

    object DatabaseBuilder {
        private var db: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (db == null) {
                db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "shopping_list.db"
                ).build()
            }
            return db!!
        }
    }

    fun loadProducts(context: Context, shoppingListId: Int) {
        val db = DatabaseBuilder.getInstance(context)
        val productDao = db.productDAO()
        CoroutineScope(Dispatchers.IO).launch {
            products = productDao.getProducts(shoppingListId).toMutableList()
        }
    }

    fun loadShoppingLists(context: Context) {
        val db = DatabaseBuilder.getInstance(context)
        val shoppingListDao = db.shoppingListDAO()
        CoroutineScope(Dispatchers.IO).launch {
            shoppingLists = shoppingListDao.getShoppingLists().toMutableList()
        }
    }

    fun addProductFromHandler(savedStateHandle: SavedStateHandle?) {
        val productName = savedStateHandle?.get<String>("productName")
        if (productName == null) return
        var productQuantity = 1
        val quantity = savedStateHandle.get<String>("productQuantity")
        if (quantity != null)
            productQuantity = quantity.toInt()
        val productBrand = savedStateHandle.get<String>("productBrand")
        val productDescription = savedStateHandle.get<String>("productDescription")
        var productPrice:Float? = null
        val price = savedStateHandle.get<String>("productPrice")
        if (price != null)
            productPrice = price.toFloat()
        val productCategory = savedStateHandle.get<String>("productCategory")
        val product = ProductData(
            id = null,
            productQuantity = productQuantity,
            productName = productName,
            productBrand = productBrand ?: "",
            productDescription = productDescription ?: "",
            productCategory = productCategory ?: "",
            productPrice = productPrice,
            shoppingListId = null
        )
        val db = DatabaseBuilder.getInstance(savedStateHandle.get<Context>("context")!!)
        val productDao = db.productDAO()
        CoroutineScope(Dispatchers.IO).launch {
            productDao.insert(product)
        }
        addProduct(product)
    }

    fun addProduct(product: ProductData) {
        products.add(product)
    }

    fun removeProduct(product: ProductData) {
        products.remove(product)
    }

    fun updateProduct(product: ProductData) {
        val index = products.indexOfFirst { it.id == product.id }
        if (index != -1) {
            products[index] = product
        }
    }

    fun getProductById(id: Int): ProductData? {
        return products.find { it.id == id }
    }
}