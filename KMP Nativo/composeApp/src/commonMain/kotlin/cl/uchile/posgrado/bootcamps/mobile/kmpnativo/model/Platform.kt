package cl.uchile.posgrado.bootcamps.mobile.kmpnativo.model

interface Platform {
    val name: String
}

// Nombre es común para todas las plataformas y equivalente
expect fun getPlatformName(): Platform

// Directorio de usuario es para todas la plataformas pero se implementan distinto
expect fun getUserHomeDir(): String

// Nivel de batería solo funciona para Android (móviles) pero no para desktop
interface BatteryLevel {
    fun getBatteryLevel(): Int?
}

// Nombre del usuario conectado solo funciona en desktop, pero no en Android (móviles)
expect fun getSystemUserName(): String?