package cl.uchile.postgrado.mobile.catalogo.model.repository

import cl.uchile.postgrado.mobile.catalogo.model.data.Product

interface CatalogRepository {
    fun addProduct(product: Product, onResult: (Boolean) -> Unit)
    fun getProducts(onResult: (List<Product>) -> Unit)
}