package cl.uchile.postgrado.mobile.cloudmessagingexample.model

import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    fun observeMessages(): Flow<String>
}