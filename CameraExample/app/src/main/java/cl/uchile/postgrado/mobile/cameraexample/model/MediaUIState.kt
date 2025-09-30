package cl.uchile.postgrado.mobile.cameraexample.model

import android.net.Uri

sealed class MediaUIState {
    object Idle : MediaUIState()
    object Loading : MediaUIState()
    data class Success(val uri: Uri) : MediaUIState()
    data class Error(val message: String) : MediaUIState()
}