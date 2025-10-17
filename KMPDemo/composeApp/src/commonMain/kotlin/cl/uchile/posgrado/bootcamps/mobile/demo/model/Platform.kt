package cl.uchile.posgrado.bootcamps.mobile.demo.model

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform