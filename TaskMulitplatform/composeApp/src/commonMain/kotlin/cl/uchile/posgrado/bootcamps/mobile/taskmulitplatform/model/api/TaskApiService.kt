package cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.model.api

import cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.model.Task
import io.ktor.client.call.body
import io.ktor.client.request.get

class TaskApiService {
    private val client = HttpClientFactory.client
    private val baseUrl = "https://jsonplaceholder.typicode.com"

    suspend fun getTasks(): List<Task> {
        return client.get("$baseUrl/todos?_limit=5").body()
    }
}