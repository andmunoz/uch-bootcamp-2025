package cl.uchile.postgrado.mobile.additionalexamples.model

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class SyncWorker(context: Context, params: WorkerParameters)
    : CoroutineWorker(context, params) {
        override suspend fun doWork(): Result {
            // SINCRONIZAMOS EL SERVICIO
            Log.d("SyncWorker", "Sincronización realizada ${System.currentTimeMillis()}")
            return Result.success()
        }
}