package cl.uchile.posgrado.bootcamps.mobile.kmpnativo.model

class Greeting {
    private val platform = getPlatformName()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}