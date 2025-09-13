package cl.uchile.postgrado.mobile.catalogo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.uchile.postgrado.mobile.catalogo.model.repository.CatalogRepository
import cl.uchile.postgrado.mobile.catalogo.model.repository.ImageStorageRepository

class CatalogViewModelFactory(
    private val productRepository: CatalogRepository,
    private val imageStorageRepository: ImageStorageRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatalogViewModel::class.java)) {
            return CatalogViewModel(productRepository, imageStorageRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}