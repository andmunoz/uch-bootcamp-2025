package cl.uchile.postgrado.mobile.shoppinglist.ui.screens.shoppingListScreen

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import cl.uchile.postgrado.mobile.shoppinglist.model.ProductData
import org.json.JSONObject
import org.json.JSONArray

class ShoppingListViewModel : ViewModel() {
    var products = mutableListOf<ProductData>()
        private set

    fun addProductFromHandler(savedStateHandle: SavedStateHandle?) {
        val productName = savedStateHandle?.get<String>("productName")
        if (productName == null) return
        var productQuantity = savedStateHandle.get<Int>("productQuantity")
        if (productQuantity == null)
            productQuantity = 1
        val productBrand = savedStateHandle.get<String>("productBrand")
        val productDescription = savedStateHandle.get<String>("productDescription")
        val productPrice = savedStateHandle.get<String>("productPrice")
        val productCategory = savedStateHandle.get<String>("productCategory")
        val product = ProductData(
            id = products.size + 1,
            productQuantity = productQuantity,
            productName = productName,
            productBrand = productBrand ?: "",
            productDescription = productDescription ?: "",
            productCategory = productCategory ?: "",
            productPrice = productPrice ?: ""
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

    fun loadProducts(context: Context) {
        try {
            val json = context.openFileInput("shopping_list.json").bufferedReader().use {
                it.readText()
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
                val productPrice = product.getString("productPrice")
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
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun saveProducts(context: Context) {
        val json = JSONObject()
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
        context.openFileOutput("shopping_list.json", Context.MODE_PRIVATE).use {
            it.write(json.toString().toByteArray())
        }
    }
}