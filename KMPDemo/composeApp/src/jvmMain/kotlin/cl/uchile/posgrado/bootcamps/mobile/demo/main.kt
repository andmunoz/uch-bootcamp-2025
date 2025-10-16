package cl.uchile.posgrado.bootcamps.mobile.demo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "demo",
    ) {
        App()
    }
}