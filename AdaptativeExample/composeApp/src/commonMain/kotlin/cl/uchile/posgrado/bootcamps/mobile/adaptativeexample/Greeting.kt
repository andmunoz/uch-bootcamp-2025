package cl.uchile.posgrado.bootcamps.mobile.adaptativeexample

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}