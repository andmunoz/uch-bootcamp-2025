package cl.uchile.postgrado.mobile.catalogo.model.repository

import cl.uchile.postgrado.mobile.catalogo.model.data.Producto

interface ProductRepository {
    fun addProduct(product: Producto, onResult: (Boolean) -> Unit)
    fun getProducts(onResult: (List<Producto>) -> Unit)
}