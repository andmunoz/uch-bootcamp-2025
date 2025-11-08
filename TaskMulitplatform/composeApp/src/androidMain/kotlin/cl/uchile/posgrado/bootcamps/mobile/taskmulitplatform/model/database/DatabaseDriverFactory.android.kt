package cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.model.database

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

/* actual class DatabaseDriverFactory(val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(TasksDatabase.Schema, context, "taskmulitplatform.db")
    }
} */