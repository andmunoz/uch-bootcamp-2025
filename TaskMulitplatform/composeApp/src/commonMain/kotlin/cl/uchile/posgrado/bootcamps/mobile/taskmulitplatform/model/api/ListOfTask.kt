package cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.model.api

import cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.model.database.Task
import kotlinx.serialization.Serializable

@Serializable
class ListOfTask {
    val tasks = mutableListOf<Task>()
}
