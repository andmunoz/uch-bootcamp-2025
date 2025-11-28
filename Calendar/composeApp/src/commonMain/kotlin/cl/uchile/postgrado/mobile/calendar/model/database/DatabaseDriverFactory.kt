package cl.uchile.postgrado.mobile.calendar.model.database

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}