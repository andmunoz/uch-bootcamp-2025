package cl.uchile.posgrado.bootcamps.mobile.persistenciaynetworkingmultiplaforma.sqldelight

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}