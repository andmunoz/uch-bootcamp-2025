package cl.uchile.posgrado.bootcamps.mobile.demo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cl.uchile.posgrado.bootcamps.mobile.demo.ui.AppDesktop

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Hola Mundo",
    ) {
        AppDesktop()
    }
}