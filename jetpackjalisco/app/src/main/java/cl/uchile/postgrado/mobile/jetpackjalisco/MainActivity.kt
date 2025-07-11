package cl.uchile.postgrado.mobile.jetpackjalisco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
            // Llamamos a la función composable
            JaliscoApp()
        }
    }
}

@Composable
fun JaliscoApp() {
    // Definimos las variables que manejarán los valores que aparecerán en pantalla
    var userInput by remember { mutableStateOf("") }
    var jaliscoAnswer by remember { mutableStateOf<String?>(null) }

    // Pondremos una distrubución de eleentontos en columna
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Definimos el primer widget como un texto (título)
        Text(
            text = "Jalisco Nunca Pierde",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Red,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )

        HorizontalDivider()
        VerticalDivider()

        // Ponemos el campo de texto donde el usuario ingresa el número
        TextField(
            value = userInput,
            onValueChange = { userInput = it },
            label = { Text("Tu número") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            placeholder = { Text("Número entre 1 y 100") },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedLabelColor = Color.Red,
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = Color.Gray
            )
        )

        // Agregamos el botón de acción que, en este caso, lo podemos programar inmediatamente
        Button(
            onClick = {
                val numero = userInput.toIntOrNull()
                if (numero != null && numero >= 1 && numero <= 100) {
                    jaliscoAnswer = "¡Yo te gano con el ${numero + 1}!"
                }
                else {
                    jaliscoAnswer = "No hagas trampa, debe ser un número entre 1 y 100"
                }
            },
            enabled = userInput.isNotBlank(),
            shape = MaterialTheme.shapes.small,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            )
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Jugar"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Jugar")
        }

        // Mostramos la respuesta de Jalisco, siempre y cuando haya una
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