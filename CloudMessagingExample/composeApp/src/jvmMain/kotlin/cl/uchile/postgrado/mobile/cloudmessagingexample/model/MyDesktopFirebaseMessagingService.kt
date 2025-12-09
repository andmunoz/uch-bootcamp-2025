package cl.uchile.postgrado.mobile.cloudmessagingexample.model

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.awt.Desktop

class MyDesktopFirebaseMessagingService {
    // ToDo: Recepción de mensajes en app Desktop
    fun onMessageReceive(message: String) {
        GlobalScope.launch() {
            DesktopNotificationRepository.emit(message)
        }
    }
}