package cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.model.Task
import cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.model.api.TaskApiService
// import cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.model.database.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch

class TasksViewModel: ViewModel() {
    private val apiService = TaskApiService()

    private var _taskList = MutableStateFlow(listOf<Task>())
    val taskList: StateFlow<List<Task>> = _taskList.asStateFlow()

    init {
        viewModelScope.launch {
            _taskList.value = apiService.getTasks()
            taskList.value.forEach { task ->
                addTask(task)
            }
        }
    }

    fun addTask(task: Task) {
        // repository.insertTask(task.title)
    }
}
