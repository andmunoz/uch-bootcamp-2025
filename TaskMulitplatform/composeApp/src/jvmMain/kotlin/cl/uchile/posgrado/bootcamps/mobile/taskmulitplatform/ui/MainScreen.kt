package cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MainScreen() {
    Column {
        Button(
            onClick = {
                // TODO
            }
        ) {
            Text("Agregar")
        }
        TaskScreen()
    }
}