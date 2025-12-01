package cl.uchile.postgrado.mobile.calendar

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cl.uchile.postgrado.mobile.calendar.model.database.DatabaseDriverFactory
import cl.uchile.postgrado.mobile.calendar.model.database.HolidayDatabase
import cl.uchile.postgrado.mobile.calendar.view.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Calendar",
    ) {
        val driver = DatabaseDriverFactory()
        val database = HolidayDatabase(driver)
        App(database)
    }
}