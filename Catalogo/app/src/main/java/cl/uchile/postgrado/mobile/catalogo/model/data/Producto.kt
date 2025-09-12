package cl.uchile.postgrado.mobile.catalogo.model.data

data class Producto(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val imagenUrl: String
)

data class Venta(
    val id: String,
    val fecha: String,
    val total: Double,
    val productos: List<Producto>
)