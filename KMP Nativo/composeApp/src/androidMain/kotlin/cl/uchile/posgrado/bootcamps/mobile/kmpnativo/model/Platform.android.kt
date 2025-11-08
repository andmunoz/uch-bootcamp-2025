package cl.uchile.posgrado.bootcamps.mobile.kmpnativo.model

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.Environment
import cl.uchile.posgrado.bootcamps.mobile.kmpnativo.MainActivity

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatformName(): Platform = AndroidPlatform()

actual fun getUserHomeDir(): String = Environment.getExternalStorageDirectory().absolutePath

class AndroidBatteryLevel(private val context: Context) : BatteryLevel {
    override fun getBatteryLevel(): Int? {
        val batteryIntent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val level = batteryIntent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale = batteryIntent?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        return if (level == -1 || scale == -1) {
            null
        } else {
            (level.toFloat() / scale.toFloat() * 100.0f).toInt()
        }
    }
}

actual fun getSystemUserName(): String? {
    // Opción 1: ELEGANTE
    throw UnsupportedOperationException("Está funcionalidad no está disponible en Android")

    // Opción 2: Común
    // return null
}