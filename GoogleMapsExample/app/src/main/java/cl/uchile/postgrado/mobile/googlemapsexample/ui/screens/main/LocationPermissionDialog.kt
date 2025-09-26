package cl.uchile.postgrado.mobile.googlemapsexample.ui.screens.main

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LocationPermissionDialog(
    onRequestPermission: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Permiso de ubicación requerido") },
        text = { Text("Este aplicativo necesita el permiso de ubicación para funcionar correctamente.") },
        confirmButton = {
            Button(onClick = onRequestPermission) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}