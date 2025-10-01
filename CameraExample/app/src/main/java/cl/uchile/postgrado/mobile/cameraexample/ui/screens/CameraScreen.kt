package cl.uchile.postgrado.mobile.cameraexample.ui.screens

import android.Manifest
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import cl.uchile.postgrado.mobile.cameraexample.model.MediaUIState
import cl.uchile.postgrado.mobile.cameraexample.viewmodel.MediaViewModel
import coil.compose.AsyncImage
import java.io.File

@Composable
fun CameraScreen() {
    val context = LocalContext.current
    val viewModel: MediaViewModel = MediaViewModel()
    val uiState by viewModel.mediaUIState.collectAsState()
    var tempUri by remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempUri != null) {
            viewModel.onMediaCaptured(tempUri!!)
        } else {
            viewModel.onError("Error capturing image")
        }
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            viewModel.onMediaCaptured(uri)
        } else {
            viewModel.onError("Error selecting image")
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val uri = createImageUri(context)
            tempUri = uri
            cameraLauncher.launch(uri)
        } else {
            viewModel.onError("Camera permission denied")
        }
    }

    Scaffold{
        Column(
            Modifier
                .padding(it)
        ) {
            Row {
                Button(
                    onClick = {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    },
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(0.5f)
                ) {
                    Text("Camera")
                }
                Button(
                    onClick = {
                        galleryLauncher.launch("image/*")
                    },
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(0.5f)
                ) {
                    Text("Gallery")
                }
            }
            when (uiState) {
                is MediaUIState.Idle -> Text("Waiting for action...")
                is MediaUIState.Loading -> CircularProgressIndicator()
                is MediaUIState.Success -> {
                    val uri = (uiState as MediaUIState.Success).uri
                    Log.d("CameraScreen", "uri: $uri")
                    AsyncImage(
                        model = uri,
                        contentDescription = "Selected or captured image"
                    )
                    /* GlideImage(
                    model = tempUri,
                    contentDescription = "Selected or captured image"
                    ) */
                }
                is MediaUIState.Error -> Text("Error: ${(uiState as MediaUIState.Error).message}")
            }
        }
    }
}

fun createImageUri(context: Context): Uri {
    val file = File(
        context.cacheDir,
        "photo_${System.currentTimeMillis()}.jpg"
    )
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )
}