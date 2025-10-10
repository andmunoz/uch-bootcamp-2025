package cl.uchile.postgrado.mobile.additionalexamples.model

import android.util.Log
import cl.uchile.postgrado.mobile.additionalexamples.viewmodel.NotificationViewModel
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    private val notificationViewModel = NotificationViewModel()

    override fun onNewToken(token: String) {
        Log.d("MyFirebaseMessagingService", "Refreshed token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification?.title ?: "Notificación"
        val message = remoteMessage.notification?.body ?: ""
        notificationViewModel.showNotification(applicationContext, title, message)
        Log.d("MyFirebaseMessagingService", "Título: $title, Mensaje: $message")
    }
}