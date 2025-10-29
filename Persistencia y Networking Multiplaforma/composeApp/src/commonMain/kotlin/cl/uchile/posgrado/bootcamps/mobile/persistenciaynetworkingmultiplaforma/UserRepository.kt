package cl.uchile.posgrado.bootcamps.mobile.persistenciaynetworkingmultiplaforma

import cl.uchile.posgrado.bootcamps.mobile.persistenciaynetworkingmultiplaforma.ktor.UserApi
import cl.uchile.posgrado.bootcamps.mobile.persistenciaynetworkingmultiplaforma.ktor.UserApiService
import cl.uchile.posgrado.bootcamps.mobile.persistenciaynetworkingmultiplaforma.sqldelight.Database

class UserRepository (
    private val api: UserApiService,
    private val database: Database
) {
    suspend fun getUsersFromApi(): List<UserApi> = api.getUsers()
    suspend fun getUsersFromDb(): List<UserApi> = api.getUsers()
}