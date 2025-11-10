package cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.ui

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MainScreen() {
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
        TaskScreen()
    }
}