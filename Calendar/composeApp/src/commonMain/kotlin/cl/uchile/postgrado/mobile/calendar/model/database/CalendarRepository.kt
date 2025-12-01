package cl.uchile.postgrado.mobile.calendar.model.database

import cl.uchile.postgrado.mobile.calendar.database.Holiday

class CalendarRepository(private val database: HolidayDatabase) {
    fun getHoliday(id: Long) = database.taskQueries.selectHolidayFromId(id).executeAsOne()
    fun getHolidaysFromDate(year: Long, month: Long, day: Long) = database.taskQueries.selectHolidayFromDate(year, month, day).executeAsOneOrNull()
    fun getHolidaysFromMonth(month: Long, year: Long, country: String) = database.taskQueries.selectHolidaysFromMonth(month, year, country).executeAsList()
    fun insertHoliday(holiday: Holiday) = database.taskQueries.insertHoliday(holiday.year, holiday.month, holiday.day, holiday.description, holiday.country)
    fun removeHoliday(id: Long) = database.taskQueries.removeHoliday(id)
}
