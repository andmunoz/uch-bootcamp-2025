package cl.uchile.posgrado.bootcamps.mobile.kmpnativo.model

import androidx.compose.ui.awt.ComposeWindow
import java.awt.FileDialog

actual fun getUserName(): String {
    return System.getProperty("user.name") ?: ""
}

actual suspend fun getPhoto(): String? {
    val fd = FileDialog(ComposeWindow(), "Seleccionar foto", FileDialog.LOAD)
    fd.isVisible = true
    val file = fd.files.firstOrNull()
    return file?.absolutePath
}