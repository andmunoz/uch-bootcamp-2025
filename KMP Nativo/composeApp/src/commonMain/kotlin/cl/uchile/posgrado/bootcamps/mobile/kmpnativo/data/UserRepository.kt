package cl.uchile.posgrado.bootcamps.mobile.kmpnativo.data

import cl.uchile.posgrado.bootcamps.mobile.kmpnativo.data.AppDatabase
import cl.uchile.posgrado.bootcamps.mobile.kmpnativo.model.User

class UserRepository(driverFactory: DriverFactory) {
    private val db = AppDatabase(driverFactory.createDriver())
    private val dbQuery = db.userQueries

    fun insertUser(username: String, fullname: String, photo: String?) {
        dbQuery.insertUser(username, fullname, photo)
    }

    fun getUsers(): List<User> {
        return dbQuery.selectAll().executeAsList()
    }
}