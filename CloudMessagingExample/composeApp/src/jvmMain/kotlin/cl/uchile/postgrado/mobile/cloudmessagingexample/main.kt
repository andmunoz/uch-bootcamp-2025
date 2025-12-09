package cl.uchile.postgrado.mobile.cloudmessagingexample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cl.uchile.postgrado.mobile.cloudmessagingexample.model.DesktopNotificationRepository
import cl.uchile.postgrado.mobile.cloudmessagingexample.viewmodel.NotificationViewModel

fun main() = application {
    val viewModel = NotificationViewModel(DesktopNotificationRepository)
    Window(
        onCloseRequest = ::exitApplication,
        title = "CloudMessagingExample",
    ) {
        App(viewModel)
    }
}