package cl.uchile.postgrado.mobile.cloudmessagingexample

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import cl.uchile.postgrado.mobile.cloudmessagingexample.ui.NotificationScreen
import cl.uchile.postgrado.mobile.cloudmessagingexample.viewmodel.NotificationViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(viewModel: NotificationViewModel) {
    MaterialTheme {
        NotificationScreen(viewModel)
    }
}