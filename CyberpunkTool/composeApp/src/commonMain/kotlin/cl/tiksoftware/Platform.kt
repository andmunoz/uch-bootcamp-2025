package cl.tiksoftware

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform