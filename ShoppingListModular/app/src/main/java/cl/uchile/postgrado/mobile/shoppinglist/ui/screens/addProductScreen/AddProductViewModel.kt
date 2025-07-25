package cl.uchile.postgrado.mobile.shoppinglist.ui.screens.addProductScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class AddProductViewModel : ViewModel() {
    var productName by mutableStateOf("")
        private set
    
    fun onProductNameChange(value: String) {
        productName = value
    }

    var productBrand by mutableStateOf("")
        private set

    fun onProductBrandChange(value: String) {
        productBrand = value
    }

    var productDescription by mutableStateOf("")
        private set

    fun onProductDescriptionChange(value: String) {
        productDescription = value
    }

    var productPrice by mutableStateOf("")
        private set

    fun onProductPriceChange(value: String) {
        productPrice = value
    }

    var productCategory by mutableStateOf("")
        private set

    fun onProductCategoryChange(value: String) {
        productCategory = value
    }

    var productNameError by mutableStateOf<String?>(null)
    var productBrandError by mutableStateOf<String?>(null)
    var productPriceError by mutableStateOf<String?>(null)

    var isFormValid by mutableStateOf(false)

    fun validateForm() {
        val productNameValidation = validateProductName(productName)
        val productBrandValidation = validateProductBrand(productBrand)
        val productPriceValidation = validateProductPrice(productPrice)

        productNameError = if (productNameValidation is validateInput.Error) {
            productNameValidation.errorMessage
        } else { null }

        productBrandError = if (productBrandValidation is validateInput.Error) {
            productBrandValidation.errorMessage
        } else { null }

        productPriceError = if (productPriceValidation is validateInput.Error) {
            productPriceValidation.errorMessage
        } else { null }

        isFormValid = productNameValidation == validateInput.Success &&
                      productBrandValidation == validateInput.Success &&
                      productPriceValidation == validateInput.Success
    }

    fun addProduct(saveStateHandle: SavedStateHandle?) {
        saveStateHandle?.set("productName", productName)
        saveStateHandle?.set("productBrand", productBrand)
        saveStateHandle?.set("productDescription", productDescription)
        saveStateHandle?.set("productPrice", productPrice)
        saveStateHandle?.set("productCategory", productCategory)
    }
}

sealed class validateInput {
    object Success : validateInput()
    data class Error(val errorMessage: String) : validateInput()
}

fun validateProductName(productName: String): validateInput {
    return if (productName.isEmpty()) {
        validateInput.Error("El nombre del producto no puede estar vacío")
    } else {
        validateInput.Success
    }
}

fun validateProductBrand(productBrand: String): validateInput {
    return if (productBrand.isEmpty()) {
        validateInput.Error("La marca del producto no puede estar vacía")
    } else {
        validateInput.Success
    }
}

fun validateProductPrice(productPrice: String): validateInput {
    return if (productPrice.isEmpty()) {
        validateInput.Error("El precio del producto no puede estar vacío")
    } else if (productPrice.toDoubleOrNull() == null) {
        validateInput.Error("El precio del producto debe ser un número")
    } else {
        validateInput.Success
    }
}