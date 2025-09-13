package cl.uchile.postgrado.mobile.catalogo.model.repository

import cl.uchile.postgrado.mobile.catalogo.model.data.Product
import com.google.firebase.firestore.FirebaseFirestore

class CatalogFirestoreRepository: CatalogRepository {
    private val db = FirebaseFirestore.getInstance()
    private val productCollection = db.collection("productos")

    override fun addProduct(
        product: Product,
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

    override fun getProducts(onResult: (List<Product>) -> Unit) {
        productCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                onResult(emptyList())
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val products = snapshot.documents.mapNotNull {
                    it.toObject(Product::class.java)
                }
                onResult(products)
            }
        }
    }

    fun getProduct(id: String, onResult: (Product?) -> Unit) {
        productCollection.document(id).get()
            .addOnSuccessListener {
                val product = it.toObject(Product::class.java)
                onResult(product)
            }
            .addOnFailureListener {
                onResult(null)
            }
    }
}