package cl.uchile.posgrado.bootcamps.mobile.adaptativeexample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform