package cl.uchile.postgrado.mobile.catalogo.model.data

class Product {
    var id: String = ""
    var nombre: String = ""
    var descripcion: String = ""
    var precio: Double = 0.0
    var imagenUrl: String = ""

    constructor() {
        // Requerido por Firebase Firestore
    }

    constructor(id: String, nombre: String, descripcion: String, precio: Double, imagenUrl: String) {
        this.id = id
        this.nombre = nombre
        this.descripcion = descripcion
        this.precio = precio
        this.imagenUrl = imagenUrl
    }

    // Necesario para Realtime Database
    constructor(id: String) {
        this.id = id
    }

    fun copy(id: String): Product {
        return Product(id)
    }
}
