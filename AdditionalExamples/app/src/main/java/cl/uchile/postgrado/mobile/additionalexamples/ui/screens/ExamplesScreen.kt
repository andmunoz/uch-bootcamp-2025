package cl.uchile.postgrado.mobile.additionalexamples.ui.screens

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import androidx.core.app.ActivityCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import cl.uchile.postgrado.mobile.additionalexamples.model.BiometricViewModel
import cl.uchile.postgrado.mobile.additionalexamples.model.SyncWorker
import cl.uchile.postgrado.mobile.additionalexamples.viewmodel.BackgroundViewModel
import cl.uchile.postgrado.mobile.additionalexamples.viewmodel.NotificationViewModel
import java.util.concurrent.TimeUnit

@Composable
fun ExamplesScreen(
    biometricViewModel: BiometricViewModel,
    notificationViewModel: NotificationViewModel = NotificationViewModel(),
    backgroundViewModel: BackgroundViewModel = BackgroundViewModel(),
    onAuthenticate: () -> Unit = {}
) {
    val context = LocalContext.current
    var titleToShare by remember { mutableStateOf(TextFieldValue("")) }
    var textToShare by remember { mutableStateOf(TextFieldValue("")) }
    var isRunning by remember { mutableStateOf(false) }
    val syncWorkRequest = OneTimeWorkRequestBuilder<SyncWorker>().build()
    val timedWorkRequest = PeriodicWorkRequestBuilder<SyncWorker>(15, TimeUnit.SECONDS).build()
    val state by biometricViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        notificationViewModel.createNotificationChannel(context)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.d("ExamplesScreen", "Permiso concedido")
        } else {
            Log.d("ExamplesScreen", "Permiso denegado")
        }
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (state.authenticated) {
                TextField(
                    value = titleToShare,
                    onValueChange = { titleToShare = it },
                    label = {
                        Text("Título a Compartir")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = textToShare,
                    onValueChange = { textToShare = it },
                    label = {
                        Text("Texto a Compartir")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                )
                {
                    Button(
                        onClick = {
                            val shareIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                type = "image/*"
                                putExtra(Intent.EXTRA_TEXT, textToShare.text)
                                putExtra(Intent.EXTRA_SUBJECT, titleToShare.text)
                                putExtra(Intent.EXTRA_TITLE, titleToShare.text)

                                /* action = Intent.ACTION_SEND_MULTIPLE
                                type = "image / *"
                                putExtra(Intent.EXTRA_STREAM, ArrayList<URI>) */

                                /* PARA COMPARTIR UNA IMAGEN
                                type = "image / *"
                                putExtra(Intent.EXTRA_STREAM, URI("https://www.marketingdirecto.com/wp-content/uploads/2015/09/android.jpg"))*/
                            }
                            val chooserIntent = Intent.createChooser(
                                shareIntent,
                                "Compartir con..."
                            )
                            context.startActivity(chooserIntent)
                        },
                        modifier = Modifier
                            .weight(0.5f)
                    ) {
                        Text("Compartir")
                    }
                    Button(
                        onClick = {
                            if (titleToShare.text.isNotEmpty() && textToShare.text.isNotEmpty()) {
                                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                                if (ActivityCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.POST_NOTIFICATIONS
                                    ) != PackageManager.PERMISSION_GRANTED
                                ) {
                                    Log.d(
                                        "ExamplesScreen",
                                        "No se tienen los permisos para notificar"
                                    )
                                } else {
                                    notificationViewModel.showNotification(
                                        context,
                                        titleToShare.text,
                                        textToShare.text
                                    )
                                }
                            }
                        },
                        modifier = Modifier
                            .weight(0.5f)
                    ) {
                        Text("Notificar")
                    }
                }
                Button(
                    onClick = {
                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        if (ActivityCompat.checkSelfPermission(
                                context,
                                Manifest.permission.POST_NOTIFICATIONS
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            Log.d("ExamplesScreen", "No se tienen los permisos para notificar")
                        } else {
                            if (isRunning) {
                                backgroundViewModel.stopTracking(context)
                            } else {
                                backgroundViewModel.startTracking(context)
                            }
                            isRunning = !isRunning
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (isRunning) "Detener" else "Iniciar")
                }
                Button(
                    onClick = {
                        WorkManager.getInstance(context).enqueue(syncWorkRequest)
                        WorkManager.getInstance(context).enqueue(timedWorkRequest)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Sincronizar Datos")
                }
            }
            else {
                Button(
                    onClick = {
                        onAuthenticate()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Autenticar con Huella Digital")
                }
            }
        }
    }
}
