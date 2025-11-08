package cl.uchile.posgrado.bootcamps.mobile.kmpynativo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform