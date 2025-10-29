package cl.uchile.posgrado.bootcamps.mobile.persistenciaynetworkingmultiplaforma

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}