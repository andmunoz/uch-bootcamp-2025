package cl.uchile.posgrado.bootcamps.mobile.persistenciaynetworkingmultiplaforma.sqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import cl.uchile.postgrado.bootcamp.mobile.AppDatabaseExample

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver = JdbcSqliteDriver("jdbc:sqlite:app.db")
        AppDatabaseExample.Schema.create(driver)
        return driver
    }
}