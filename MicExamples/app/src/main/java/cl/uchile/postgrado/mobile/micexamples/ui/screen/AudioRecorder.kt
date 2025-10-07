package cl.uchile.postgrado.mobile.micexamples.ui.screen

import android.Manifest
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import cl.uchile.postgrado.mobile.micexamples.model.AudioUIState
import cl.uchile.postgrado.mobile.micexamples.viewmodel.AudioViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AudioRecorderScreen(viewModel: AudioViewModel = AudioViewModel()) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.startRecording(context)
        } else {
            Toast.makeText(context, "Permiso de grabación de audio denegado", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize().padding(it)
        ) {
            when (uiState) {
                is AudioUIState.Idle -> {
                    Button(onClick = {
                        permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                    }) {
                        Text("Grabar Audio")
                    }
                }

                is AudioUIState.Recording -> {
                    Button(onClick = { viewModel.stopRecording() }) {
                        Text("Detener Grabación")
                    }
                }

                is AudioUIState.Error -> {
                    Text("Error: ${(uiState as AudioUIState.Error).message}")
                    Button(onClick = { viewModel.stopRecording() }) {
                        Text("Reiniciar")
                    }
                }

                is AudioUIState.Success -> {
                    viewModel.restart()
                }
            }
        }
    }
}