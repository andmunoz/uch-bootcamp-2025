package cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.model.database.DatabaseDriverFactory
import cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.viewmodel.TasksViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun TaskScreen(driverFactory: DatabaseDriverFactory) {
    val tasksViewModel = TasksViewModel(driverFactory)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Mis Tareas",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        if (!tasksViewModel.taskList.value.isEmpty()) {
            tasksViewModel.taskList.value.forEach { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Checkbox(
                        checked = task.completed == 1L,
                        onCheckedChange = {
                            // ToDo
                        }
                    )
                    Text(
                        text = task.title
                    )
                }
            }
        } else {
            Text(
                text = "Cargando tareas..."
            )
        }
    }
}