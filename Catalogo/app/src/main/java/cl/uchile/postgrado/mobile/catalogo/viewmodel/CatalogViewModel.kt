package cl.uchile.postgrado.mobile.catalogo.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.postgrado.mobile.catalogo.model.repository.CatalogFirestoreRepository
import cl.uchile.postgrado.mobile.catalogo.model.repository.CatalogRepository
import cl.uchile.postgrado.mobile.catalogo.model.data.Product
import cl.uchile.postgrado.mobile.catalogo.model.repository.ImageStorageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatalogViewModel (
    private val catalogRepository: CatalogRepository = CatalogFirestoreRepository(),
    private val imageStorageRepository: ImageStorageRepository = ImageStorageRepository()
): ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        getProducts()
    }

    fun addProduct(product: Product) {
        catalogRepository.addProduct(product) { success ->
            if (success) {
                getProducts()
            }
        }
    }

    fun getProducts() {
        catalogRepository.getProducts { products ->
            _products.value = products
        }
    }

    fun uploadImage(imageUri: Uri, progressCallBack: (Double) -> Unit) {
        viewModelScope.launch {
            imageStorageRepository.uploadFile(
                fileUri = imageUri,
                remotePath = "images/${imageUri.lastPathSegment}",
                contentType = "application/image",
                progressCallback = progressCallBack
            )
        }
    }
}