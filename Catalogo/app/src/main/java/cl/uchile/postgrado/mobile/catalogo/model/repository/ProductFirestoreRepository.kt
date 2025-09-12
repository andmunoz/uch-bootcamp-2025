package cl.uchile.postgrado.mobile.catalogo.model.repository

import cl.uchile.postgrado.mobile.catalogo.model.data.Producto
import com.google.firebase.firestore.FirebaseFirestore

class ProductFirestoreRepository: ProductRepository {
    private val db = FirebaseFirestore.getInstance()
    private val productCollection = db.collection("productos")

    override fun addProduct(
        product: Producto,
        onResult: (Boolean) -> Unit,
    ) {
        productCollection.add(product)
            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }

    override fun getProducts(onResult: (List<Producto>) -> Unit) {
        productCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                onResult(emptyList())
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val products = snapshot.documents.mapNotNull {
                    it.toObject(Producto::class.java)
                }
                onResult(products)
            }
        }
    }
}