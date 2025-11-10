package cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.ui

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.model.database.DatabaseDriverFactory

@Composable
fun MainScreen(driverFactory: DatabaseDriverFactory) {
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  }
            ) {
                Text(
                    text = "Agregar"
                )
            }
        }
    ) {
        TaskScreen(driverFactory)
    }
}