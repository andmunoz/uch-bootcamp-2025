package cl.uchile.posgrado.bootcamps.mobile.kmpnativo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.posgrado.bootcamps.mobile.kmpnativo.data.UserRepository
import cl.uchile.posgrado.bootcamps.mobile.kmpnativo.model.getPhoto
import cl.uchile.posgrado.bootcamps.mobile.kmpnativo.model.getSystemUserName
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmpnativo.composeapp.generated.resources.Res
import kmpnativo.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.launch

@Composable
@Preview
fun App(userRepository: UserRepository) {
    MaterialTheme {
        var scope = rememberCoroutineScope()
        var userName by remember { mutableStateOf(getSystemUserName()) }
        var fullName by remember { mutableStateOf("") }
        var photo by remember { mutableStateOf<String?>(null) }
        val userExists = userName != ""

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Registro de Usuario", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = userName ?: "",
                onValueChange = { userName = it },
                label = { Text("Nombre de usuario") },
                enabled = !userExists
            )

            Spacer(Modifier.height(16.dp))

            Button(onClick = {
                scope.launch {
                    photo = getPhoto()
                }
            }) {
                Text("Seleccionar / Capturar Foto")
            }

            Spacer(Modifier.height(16.dp))

            /* photo?.let {
                val image = SkiaImage.makeFromEncoded(File(it).readBytes())
                Image(bitmap = image.asImageBitmap(), contentDescription = null, modifier = Modifier.size(120.dp))
            } */

            Spacer(Modifier.height(24.dp))

            Button(onClick = {
                userRepository.insertUser(userName ?: "", fullName, photo)
            }) {
                Text("Guardar Usuario")
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}