package cl.uchile.posgrado.bootcamps.mobile.kmpynativo

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}