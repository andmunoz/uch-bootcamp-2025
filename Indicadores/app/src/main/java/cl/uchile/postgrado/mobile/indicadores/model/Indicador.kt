package cl.uchile.postgrado.mobile.indicadores.model

data class Indicador(
    val codigo: String,
    val nombre: String,
    val unidad_medida: String,
    val serie: List<Serie>
) {
    data class Serie(
        val valor: Double,
        val fecha: String
    )
}
