package cl.uchile.postgrado.mobile.cloudmessagingexample.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object AndroidNotificationRepository: NotificationRepository {
    private val _flow = MutableSharedFlow<String>()

    override fun observeMessages(): Flow<String> {
        return _flow.asSharedFlow()
    }

    suspend fun emit(message: String) {
        _flow.emit(message)
    }
}