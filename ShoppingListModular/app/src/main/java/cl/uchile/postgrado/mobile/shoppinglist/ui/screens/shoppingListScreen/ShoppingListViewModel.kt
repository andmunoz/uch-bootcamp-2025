package cl.uchile.postgrado.mobile.shoppinglist.ui.screens.shoppingListScreen

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cl.uchile.postgrado.mobile.shoppinglist.model.ProductData
import cl.uchile.postgrado.mobile.shoppinglist.model.ShoppingListDBHelper

class ShoppingListViewModel : ViewModel() {
    companion object {
        lateinit var dbHelper: ShoppingListDBHelper
    }

    var products = mutableListOf<ProductData>()
        private set

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
            id = products.size + 1,
            productQuantity = productQuantity,
            productName = productName,
            productBrand = productBrand ?: "",
            productDescription = productDescription ?: "",
            productCategory = productCategory ?: "",
            productPrice = productPrice
        )
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

    fun getDbHelper(context: Context) {
        dbHelper = ShoppingListDBHelper(context)
    }

    fun loadProducts(context: Context) {
        /* val json = "{ products: [] }"
        try {
            val json = context.openFileInput("shopping_list.json").bufferedReader().use {
                it.readText()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val productList = JSONObject(json)
        val productArray = productList.getJSONArray("products")
        for (i in 0 until productArray.length()) {
            val product = productArray.getJSONObject(i)
            val id = product.getInt("id")
            val productQuantity = product.getInt("productQuantity")
            val productName = product.getString("productName")
            val productBrand = product.getString("productBrand")
            val productDescription = product.getString("productDescription")
            val productCategory = product.getString("productCategory")
            val productPrice: Float? = product.getDouble("productPrice").toFloat()
            val productData = ProductData(
                id = id,
                productQuantity = productQuantity,
                productName = productName,
                productBrand = productBrand,
                productDescription = productDescription,
                productCategory = productCategory,
                productPrice = productPrice
            )
            products.add(productData)
        } */
        val products = dbHelper.getProducts()
        this.products = products.toMutableList()
    }

    fun saveProducts(context: Context) {
        /* val json = JSONObject()
        val productArray = JSONArray()
        for (product in products) {
            val productObject = JSONObject()
            productObject.put("id", product.id)
            productObject.put("productQuantity", product.productQuantity)
            productObject.put("productName", product.productName)
            productObject.put("productBrand", product.productBrand)
            productObject.put("productDescription", product.productDescription)
            productObject.put("productCategory", product.productCategory)
            productObject.put("productPrice", product.productPrice)
            productArray.put(productObject)
        }
        json.put("products", productArray)
        try {
            context.openFileOutput("shopping_list.json", Context.MODE_PRIVATE).use {
                it.write(json.toString().toByteArray())
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
        } */
        for (product in products) {
            dbHelper.addOrUpdateProduct(product)
        }
    }
}