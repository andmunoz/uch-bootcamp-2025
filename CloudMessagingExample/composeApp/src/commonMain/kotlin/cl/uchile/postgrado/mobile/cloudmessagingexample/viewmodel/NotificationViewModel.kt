package cl.uchile.postgrado.mobile.cloudmessagingexample.viewmodel

import androidx.lifecycle.ViewModel
import cl.uchile.postgrado.mobile.cloudmessagingexample.model.NotificationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val repository: NotificationRepository
): ViewModel() {
    private val scope = CoroutineScope(Dispatchers.Default)

    private val _messages = MutableStateFlow<String?>(null)
    val messages: StateFlow<String?> = _messages

    init {
        scope.launch {
            repository.observeMessages().collect { msg ->
                _messages.value = msg
            }
        }
    }
}