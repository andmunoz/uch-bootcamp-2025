package cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.ui.MainScreen

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Task Multiplatform",
    ) {
        MainScreen()
    }
}