package cl.uchile.postgrado.mobile.additionalexamples.model

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class TrackingService: Service() {
    private val CHANNEL_ID = "tracking_channel"

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Servicio de Seguimiento")
            .setContentText("El servicio de seguimiento está en ejecución")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .build()

        startForeground(1, notification)
        Log.d("TrackingService", "Servicio de seguimiento iniciado")

        Thread {
            for (i in 1..10) {
                Thread.sleep(1000)
                Log.d("TrackingService", "Contador: $i")
            }
            stopSelf()
        }.start()

        return START_STICKY
    }

    private fun createNotificationChannel() {
        val name = "Canal de Seguimiento"
        val descriptionText = "Descripción del Canal de Seguimiento"
        val importance = NotificationManager.IMPORTANCE_LOW
        val serviceChannel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(serviceChannel)
        Log.d("TrackingService", "Canal de notificación creado")
    }
}