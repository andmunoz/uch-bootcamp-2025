package cl.uchile.posgrado.bootcamps.mobile.persistenciaynetworkingmultiplaforma

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform