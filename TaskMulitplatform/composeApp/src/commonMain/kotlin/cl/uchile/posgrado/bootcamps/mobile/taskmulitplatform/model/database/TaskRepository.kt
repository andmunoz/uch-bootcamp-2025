package cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.model.database

class TaskRepository(private val db: TaskDatabase) {
    fun getAll() = db.taskQueries.selectAll().executeAsList()
    fun insert(title: String) = db.taskQueries.insertTask(title)
    fun update(id: Long, title: String) = db.taskQueries.updateTask(title, id)
    fun delete(id: Long) = db.taskQueries.removeTask(id)
}