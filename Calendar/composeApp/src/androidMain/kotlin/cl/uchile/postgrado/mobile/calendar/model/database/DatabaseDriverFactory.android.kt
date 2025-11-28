package cl.uchile.postgrado.mobile.calendar.model.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class DatabaseDriverFactory(context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(CalendarDatabase.Schema, context, "calendar.db")
    }
}