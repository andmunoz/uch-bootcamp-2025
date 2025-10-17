package cl.uchile.posgrado.bootcamps.mobile.demo.viewmodel

import cl.uchile.posgrado.bootcamps.mobile.demo.model.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(name: String): String {
        return "Hola ${name} desde ${platform.name}!"
    }
}