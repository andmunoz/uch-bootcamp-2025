package cl.uchile.postgrado.mobile.additionalexamples.model

data class BiometricUIState(
    val authenticated: Boolean = false,
    val message: String = "Esperando autenticación..."
)
