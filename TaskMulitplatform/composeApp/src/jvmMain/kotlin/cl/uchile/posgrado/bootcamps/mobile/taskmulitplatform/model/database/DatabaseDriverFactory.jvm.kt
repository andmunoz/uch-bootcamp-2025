package cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.model.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver("jdbc:sqlite:taskmulitplatform.db")
        AppDatabase.Schema.create(driver)
        return driver
    }
}