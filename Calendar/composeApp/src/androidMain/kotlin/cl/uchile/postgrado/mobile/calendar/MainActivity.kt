package cl.uchile.postgrado.mobile.calendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cl.uchile.postgrado.mobile.calendar.model.database.DatabaseDriverFactory
import cl.uchile.postgrado.mobile.calendar.model.database.HolidayDatabase
import cl.uchile.postgrado.mobile.calendar.view.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val driver = DatabaseDriverFactory(this)
        val database = HolidayDatabase(driver)

        setContent {
            App(database)
        }
    }
}
