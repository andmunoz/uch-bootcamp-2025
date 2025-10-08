package cl.uchile.postgrado.mobile.micexamples.ui.screen

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import cl.uchile.postgrado.mobile.micexamples.model.AudioUIState
import cl.uchile.postgrado.mobile.micexamples.viewmodel.AudioViewModel
import java.util.Locale

@Composable
fun AudioRecorderScreen(viewModel: AudioViewModel = AudioViewModel()) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    var recognizedText by remember { mutableStateOf("") }
    var tts: TextToSpeech? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale("es", "CL")
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            tts?.stop()
            tts?.shutdown()
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.startRecording(context)
        } else {
            Toast.makeText(
                context,
                "Permiso de grabación de audio denegado",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    val speechLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val speechResult = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            recognizedText = speechResult?.get(0) ?: "No pude reconocer el texto"
        } else {
            Toast.makeText(context, "No pude reconocer la voz", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(
                text = recognizedText,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            when (uiState) {
                is AudioUIState.Idle -> {
                    Button(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        onClick = { permissionLauncher.launch(Manifest.permission.RECORD_AUDIO) }
                    ) {
                        Text("Grabar Audio")
                    }
                    Button(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        onClick = {
                            viewModel.setRecording()
                            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                                putExtra(
                                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                                )
                                putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-CL")
                                putExtra(RecognizerIntent.EXTRA_PROMPT, "Ahora habla...")
                            }
                            speechLauncher.launch(intent)
                    }) {
                        Text("Reconocer Voz")
                    }
                    Button(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        onClick = {
                            tts?.speak(
                                TextFieldValue(recognizedText).text,
                                TextToSpeech.QUEUE_FLUSH,
                                null,
                                "tts1")
                        },
                        enabled = recognizedText.isNotEmpty()
                    ) {
                        Text("Leer Texto")
                    }
                }

                is AudioUIState.Recording -> {
                    Button(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        onClick = { viewModel.stopRecording() }) {
                        Text("Detener Grabación")
                    }
                }

                is AudioUIState.Error -> {
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        text = "Error: ${(uiState as AudioUIState.Error).message}")
                    Button(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        onClick = { viewModel.stopRecording() }) {
                        Text("Reiniciar")
                    }
                }

                is AudioUIState.Success -> {
                    viewModel.setIdle()
                }
            }
        }
    }
}