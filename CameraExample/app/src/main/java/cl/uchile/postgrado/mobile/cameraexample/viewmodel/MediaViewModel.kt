package cl.uchile.postgrado.mobile.cameraexample.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import cl.uchile.postgrado.mobile.cameraexample.model.MediaUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MediaViewModel: ViewModel() {
    private val _mediaUIState = MutableStateFlow<MediaUIState>(MediaUIState.Idle)
    val mediaUIState: StateFlow<MediaUIState> = _mediaUIState

    fun onMediaCaptured(uri: Uri) {
        _mediaUIState.value = MediaUIState.Success(uri)
    }

    fun onError(message: String) {
        _mediaUIState.value = MediaUIState.Error(message)
    }
}
