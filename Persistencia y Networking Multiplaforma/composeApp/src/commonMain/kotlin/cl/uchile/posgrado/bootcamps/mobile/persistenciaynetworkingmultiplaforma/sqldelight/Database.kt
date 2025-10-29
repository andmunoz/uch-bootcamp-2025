package cl.uchile.posgrado.bootcamps.mobile.persistenciaynetworkingmultiplaforma.sqldelight

import cl.uchile.postgrado.bootcamp.mobile.AppDatabaseExample

class Database(factory: DatabaseDriverFactory) {
    private val driver = factory.createDriver()
    private val database = AppDatabaseExample(driver)

    val userQueries = database.userQueries
}