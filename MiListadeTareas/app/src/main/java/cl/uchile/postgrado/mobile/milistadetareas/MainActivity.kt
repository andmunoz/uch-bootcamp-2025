package cl.uchile.postgrado.mobile.milistadetareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.uchile.postgrado.mobile.milistadetareas.ui.theme.MiListaDeTareasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiListaDeTareasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ToDoList(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ToDoList(modifier: Modifier = Modifier) {
    var tarea by remember { mutableStateOf("") }
    val listaTareas = remember { mutableStateListOf<String?>(null) }

    Column(modifier = modifier) {
        Text(
            text = "Mi Lista de Tareas",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        TextField(
            value = tarea,
            onValueChange = { tarea = it },
            label = { Text("Nombre de la tareas") },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )

        Button(
            onClick = {
                listaTareas.add(tarea)
                tarea = ""
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            ),
            enabled = tarea.isNotBlank()
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Agregar tarea",
                modifier = Modifier.padding(end = 4.dp)
            )
            Text("Agregar tarea")
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = 8.dp,
                    vertical = 8.dp
                )
        ) {
            listaTareas.forEach { tarea ->
                tarea?.let {
                    Row(
                        modifier = Modifier
                            .padding(
                                horizontal = 8.dp,
                                vertical = 8.dp
                            )
                            .fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Bullet",
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(
                            horizontal = 8.dp,
                            vertical = 8.dp
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoListPreview() {
    MiListaDeTareasTheme {
        ToDoList()
    }
}