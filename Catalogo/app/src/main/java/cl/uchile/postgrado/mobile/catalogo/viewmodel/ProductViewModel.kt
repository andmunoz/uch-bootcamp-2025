package cl.uchile.postgrado.mobile.catalogo.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.postgrado.mobile.catalogo.model.repository.ProductFirestoreRepository
import cl.uchile.postgrado.mobile.catalogo.model.repository.ProductRepository
import cl.uchile.postgrado.mobile.catalogo.model.data.Producto
import cl.uchile.postgrado.mobile.catalogo.model.repository.StorageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel (
    private val productRepository: ProductRepository = ProductFirestoreRepository(),
    private val storageRepository: StorageRepository = StorageRepository()
): ViewModel() {
    private val _products = MutableStateFlow<List<Producto>>(emptyList())
    val products: StateFlow<List<Producto>> = _products

    fun addProduct(product: Producto) {
        productRepository.addProduct(product) { success ->
            if (success) {
                getProducts()
            }
        }
    }

    fun getProducts() {
        productRepository.getProducts { products ->
            _products.value = products
        }
    }

    fun uploadImage(imageUri: Uri, progressCallBack: (Double) -> Unit) {
        viewModelScope.launch {
            storageRepository.uploadFile(
                fileUri = imageUri,
                remotePath = "images/${imageUri.lastPathSegment}",
                contentType = "application/image",
                progressCallback = progressCallBack
            )
        }
    }
}