package cl.uchile.postgrado.mobile.jetpackjalisco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.uchile.postgrado.mobile.jetpackjalisco.ui.theme.JetpackJaliscoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            /* JetpackJaliscoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JaliscoApp()
                }
            } */
            JaliscoApp()
        }
    }
}

@Composable
fun JaliscoApp() {
    // Definimos las variables que manejarán los valores que aparecerán en pantalla
    var userInput by remember { mutableStateOf("") }
    var jaliscoAnswer by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Jalisco Nunca Pierde",
            style = MaterialTheme.typography.headlineSmall
        )

        TextField(
            value = userInput,
            onValueChange = { userInput = it },
            label = { Text("Ingresa un número entre 1 y 100") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Button(onClick = {
            val numero = userInput.toIntOrNull()
            if (numero != null && numero >= 1 && numero <= 100) {
                jaliscoAnswer = "¡Yo te gano con el ${numero + 1}!"
            }
            else {
                jaliscoAnswer = "No hagas trampa, debe ser un número entre 1 y 100"
            }
        }) {
            Text("Jugar")
        }

        jaliscoAnswer?.let {
            Text(
                text = it,
                style= MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackJaliscoTheme {
        Greeting("Android")
    }
}