package cl.uchile.postgrado.mobile.catalogo.model.repository

import cl.uchile.postgrado.mobile.catalogo.model.data.Product
import com.google.firebase.database.FirebaseDatabase

class CatalogRealtimeRepository: CatalogRepository {
    private val db = FirebaseDatabase.getInstance()
    private val products = db.getReference("productos")

    override fun addProduct(product: Product, onResult: (Boolean) -> Unit) {
        val key = products.push().key ?: return
        val productWithId = product.copy(id = key)
        products.child(key).setValue(productWithId)
            .addOnCompleteListener {
                onResult(it.isSuccessful)
            }
    }

    override fun getProducts(onResult: (List<Product>) -> Unit) {
        products.get().addOnSuccessListener {
            val products = it.children.map { dataSnapshot ->
                dataSnapshot.getValue(Product::class.java)!!
            }
            onResult(products)
        }
    }
}