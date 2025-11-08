package cl.uchile.posgrado.bootcamps.mobile.kmpnativo.model

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatformName(): Platform = JVMPlatform()

actual fun getUserHomeDir(): String = System.getProperty("user.home")

class DesktopBatterylevel : BatteryLevel {
    override fun getBatteryLevel(): Int? {
        return null
    }
}

actual fun getSystemUserName(): String? = System.getProperty("user.name") ?: "Desconocido"