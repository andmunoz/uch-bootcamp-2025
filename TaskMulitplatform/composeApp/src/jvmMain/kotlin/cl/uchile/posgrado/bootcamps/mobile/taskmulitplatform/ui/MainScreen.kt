package cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.model.database.DatabaseDriverFactory

@Composable
fun MainScreen(driverFactory: DatabaseDriverFactory) {
    Column {
        Button(
            onClick = {
                // TODO
            }
        ) {
            Text("Agregar")
        }
        TaskScreen(driverFactory)
    }
}