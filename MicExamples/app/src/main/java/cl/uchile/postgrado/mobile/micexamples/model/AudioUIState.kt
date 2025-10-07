package cl.uchile.postgrado.mobile.micexamples.model

sealed class AudioUIState {
    object Idle : AudioUIState()
    object Recording : AudioUIState()
    data class Error(val message: String) : AudioUIState()
    data class Success(val filePath: String) : AudioUIState()
}