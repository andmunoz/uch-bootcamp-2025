package cl.uchile.posgrado.bootcamps.mobile.persistenciaynetworkingmultiplaforma

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()