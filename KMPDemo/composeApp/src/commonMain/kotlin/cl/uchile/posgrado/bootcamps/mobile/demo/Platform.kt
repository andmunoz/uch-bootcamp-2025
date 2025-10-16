package cl.uchile.posgrado.bootcamps.mobile.demo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform