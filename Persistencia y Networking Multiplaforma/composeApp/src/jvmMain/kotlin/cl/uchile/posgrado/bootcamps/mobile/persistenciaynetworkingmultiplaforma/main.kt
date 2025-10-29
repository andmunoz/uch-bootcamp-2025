package cl.uchile.posgrado.bootcamps.mobile.persistenciaynetworkingmultiplaforma

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "persistenciaynetworkingmultiplaforma",
    ) {
        App()
    }
}