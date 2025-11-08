package cl.uchile.posgrado.bootcamps.mobile.kmpnativo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cl.uchile.posgrado.bootcamps.mobile.kmpnativo.data.DriverFactory
import cl.uchile.posgrado.bootcamps.mobile.kmpnativo.data.UserRepository
import cl.uchile.posgrado.bootcamps.mobile.kmpnativo.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Registro de Usuario",
    ) {
        val repo = UserRepository(DriverFactory())
        App(repo)
    }
}