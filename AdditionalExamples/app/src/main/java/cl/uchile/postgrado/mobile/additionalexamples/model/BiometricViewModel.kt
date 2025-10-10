package cl.uchile.postgrado.mobile.additionalexamples.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BiometricViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(BiometricUIState())
    val uiState: StateFlow<BiometricUIState> = _uiState.asStateFlow()

    fun setAuthenticated(authenticated: Boolean) {
        _uiState.value = if (authenticated) {
            BiometricUIState(authenticated = true, message = "Autenticación exitosa")
        } else {
            BiometricUIState(authenticated = false, message = "Autenticación fallida")
        }
    }
}