package cl.uchile.posgrado.bootcamps.mobile.persistenciaynetworkingmultiplaforma.ktor

import io.ktor.client.call.body
import io.ktor.client.request.get

class UserApiService {
    private val client = HttpClientFactory.client
    private val baseUrl = "https://jsonplaceholder.typicode.com"

    suspend fun getUsers(): List<UserApi> =
        client.get("$baseUrl/users").body()
}