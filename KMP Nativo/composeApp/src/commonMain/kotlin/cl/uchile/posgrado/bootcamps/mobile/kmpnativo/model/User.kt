package cl.uchile.posgrado.bootcamps.mobile.kmpnativo.model

data class User(
    val id: Long,
    val username: String,
    val fullname: String,
    val photo: String?
)
