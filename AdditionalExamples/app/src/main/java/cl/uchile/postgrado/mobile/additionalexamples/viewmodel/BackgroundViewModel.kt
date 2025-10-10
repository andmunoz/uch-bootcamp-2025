package cl.uchile.postgrado.mobile.additionalexamples.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import cl.uchile.postgrado.mobile.additionalexamples.model.TrackingService
import kotlin.jvm.java

class BackgroundViewModel: ViewModel() {
    fun startTracking(context: Context) {
        val intent = Intent(context, TrackingService::class.java)
        context.startForegroundService(intent)
        Log.d("BackgroundViewModel", "Servicio de seguimiento iniciado")
    }

    fun stopTracking(context: Context) {
        val intent = Intent(context, TrackingService::class.java)
        context.stopService(intent)
        Log.d("BackgroundViewModel", "Servicio de seguimiento detenido")
    }
}