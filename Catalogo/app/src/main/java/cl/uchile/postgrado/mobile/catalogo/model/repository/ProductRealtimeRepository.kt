package cl.uchile.postgrado.mobile.catalogo.model.repository

import cl.uchile.postgrado.mobile.catalogo.model.data.Producto
import com.google.firebase.database.FirebaseDatabase

class ProductRealtimeRepository: ProductRepository {
    private val db = FirebaseDatabase.getInstance()
    private val productos = db.getReference("productos")

    override fun addProduct(product: Producto, onResult: (Boolean) -> Unit) {
        val key = productos.push().key ?: return
        val productWithId = product.copy(id = key)
        productos.child(key).setValue(productWithId)
            .addOnCompleteListener {
                onResult(it.isSuccessful)
            }
    }

    override fun getProducts(onResult: (List<Producto>) -> Unit) {
        productos.get().addOnSuccessListener {
            val products = it.children.map { dataSnapshot ->
                dataSnapshot.getValue(Producto::class.java)!!
            }
            onResult(products)
        }
    }
}