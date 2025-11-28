package cl.uchile.postgrado.mobile.calendar.model.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import cl.uchile.postgrado.mobile.calendar.database.CalendarDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver("jdbc:sqlite:calendar.db")
        CalendarDatabase.Schema.create(driver)
        return driver
    }
}