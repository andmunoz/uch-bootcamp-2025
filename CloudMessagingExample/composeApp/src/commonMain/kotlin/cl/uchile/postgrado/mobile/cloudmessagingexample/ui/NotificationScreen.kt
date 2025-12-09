package cl.uchile.postgrado.mobile.cloudmessagingexample.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.postgrado.mobile.cloudmessagingexample.viewmodel.NotificationViewModel

@Composable
fun NotificationScreen(viewModel: NotificationViewModel) {
    val message by viewModel.messages.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Notificaciones Recibidas")
        Spacer(Modifier.height(8.dp))
        Text(message?:"No hay mensajes")
    }
}