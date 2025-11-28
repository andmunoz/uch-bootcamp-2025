package cl.uchile.postgrado.mobile.calendar.model.database

import cl.uchile.postgrado.mobile.calendar.database.CalendarDatabase

class HolidayDatabase(factory: DatabaseDriverFactory) {
    private val driver = factory.createDriver()
    private val database = CalendarDatabase(driver)

    val taskQueries = database.calendarDatabaseQueries
}