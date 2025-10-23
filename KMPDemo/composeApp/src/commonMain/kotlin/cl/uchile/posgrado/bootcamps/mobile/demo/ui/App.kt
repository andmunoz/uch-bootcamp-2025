package cl.uchile.posgrado.bootcamps.mobile.demo.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.posgrado.bootcamps.mobile.demo.viewmodel.Greeting
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import demo.composeapp.generated.resources.Res
import demo.composeapp.generated.resources.dcc_ec
import demo.composeapp.generated.resources.welcome_message
import org.jetbrains.compose.resources.stringResource

@Composable
@Preview
fun App() {
    var showContent by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var name by remember { mutableStateOf("") }
        Row {
            TextField(
                value = name,
                onValueChange = { name = it }
            )
            Button(
                onClick = { showContent = !showContent }
            ) {
                Text(stringResource(Res.string.welcome_message))
            }
        }
        AnimatedVisibility(showContent) {
            val greeting = remember {
                Greeting().greet(name)
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(painterResource(Res.drawable.dcc_ec), null)
                Text(
                    text = "Compose: $greeting",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .requiredHeight(30.dp)
                )
            }
        }
    }
}