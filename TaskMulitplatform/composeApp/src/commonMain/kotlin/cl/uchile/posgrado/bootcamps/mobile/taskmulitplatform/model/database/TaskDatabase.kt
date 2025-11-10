package cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.model.database

class TaskDatabase(factory: DatabaseDriverFactory) {
    private val driver = factory.createDriver()
    private val database = AppDatabase(driver)

    val taskQueries = database.appDatabaseQueries
}