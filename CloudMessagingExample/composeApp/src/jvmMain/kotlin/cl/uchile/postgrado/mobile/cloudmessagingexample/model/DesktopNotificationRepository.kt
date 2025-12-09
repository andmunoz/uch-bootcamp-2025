package cl.uchile.postgrado.mobile.cloudmessagingexample.model

import kotlinx.coroutines.flow.Flow

object DesktopNotificationRepository: NotificationRepository {
    override fun observeMessages(): Flow<String> {
        TODO("Not yet implemented")
    }

    suspend fun emit(message: String) {
        TODO("Not yet implemented")
    }
}