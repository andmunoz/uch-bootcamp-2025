package cl.uchile.postgrado.mobile.micexamples.viewmodel

import android.content.Context
import android.media.MediaRecorder
import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import cl.uchile.postgrado.mobile.micexamples.model.AudioUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AudioViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<AudioUIState>(AudioUIState.Idle)
    val uiState: StateFlow<AudioUIState> = _uiState.asStateFlow()

    private var recorder: MediaRecorder? = null
    private var outputFile: String = ""

    fun startRecording(context: Context) {
        val outputDir = context.filesDir.absolutePath
        // val outputDir = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)?.absolutePath
        outputFile = "${outputDir}/${System.currentTimeMillis()}.3gp"
        Log.d("AudioViewModel", "Output file: $outputFile")
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(outputFile)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            try {
                prepare()
                start()
                _uiState.value = AudioUIState.Recording
            } catch (e: Exception) {
                _uiState.value = AudioUIState.Error(e.message ?: "Error al iniciar la grabación")
            }
        }
    }

    fun stopRecording() {
        try {
            recorder?.apply {
                stop()
                release()
            }
            recorder = null
            _uiState.value = AudioUIState.Success(outputFile)
        } catch (e: Exception) {
            _uiState.value = AudioUIState.Error(e.message ?: "Error al detener la grabación")
        }
    }

    override fun onCleared() {
        super.onCleared()
        recorder?.release()
        recorder = null
    }
}