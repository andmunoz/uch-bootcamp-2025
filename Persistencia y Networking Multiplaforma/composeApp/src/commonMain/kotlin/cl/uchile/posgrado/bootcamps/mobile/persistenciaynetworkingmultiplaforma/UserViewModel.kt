package cl.uchile.posgrado.bootcamps.mobile.persistenciaynetworkingmultiplaforma

import androidx.compose.runtime.Composable

class UserViewModel(
    private val userRepository: UserRepository
) {
    suspend fun load() {
        val users = userRepository.getUsersFromApi()
    }
}
