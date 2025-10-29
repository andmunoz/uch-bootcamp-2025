package cl.uchile.posgrado.bootcamps.mobile.persistenciaynetworkingmultiplaforma.ktor

import kotlinx.serialization.Serializable

@Serializable
data class UserApi(
    val id: Int,
    val name: String,
    val email: String
)
