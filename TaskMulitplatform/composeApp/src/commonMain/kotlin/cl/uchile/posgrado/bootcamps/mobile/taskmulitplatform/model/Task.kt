package cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.model

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Long,
    val title: String,
    val completed: Boolean,
    val userId: Long? = null
)