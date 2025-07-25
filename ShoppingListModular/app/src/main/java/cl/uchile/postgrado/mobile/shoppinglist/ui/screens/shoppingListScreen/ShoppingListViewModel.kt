package cl.uchile.postgrado.mobile.shoppinglist.ui.screens.shoppingListScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class ShoppingListViewModel : ViewModel() {
    var products = mutableListOf<ProductData>()
        private set

    fun addProductFromHandler(savedStateHandle: SavedStateHandle?) {
        val productName = savedStateHandle?.get<String>("productName")
        val productBrand = savedStateHandle?.get<String>("productBrand")
        val productDescription = savedStateHandle?.get<String>("productDescription")
        val productPrice = savedStateHandle?.get<String>("productPrice")
        val productCategory = savedStateHandle?.get<String>("productCategory")
        val product = ProductData(
            id = products.size + 1,
            productName = productName ?: "",
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
}