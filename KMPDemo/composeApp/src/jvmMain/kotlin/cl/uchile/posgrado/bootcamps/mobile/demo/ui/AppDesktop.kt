package cl.uchile.posgrado.bootcamps.mobile.demo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AppDesktop() {
    MaterialTheme {
        Column {
            Text("Estoy en Desktop")
            App()
        }
    }
}
