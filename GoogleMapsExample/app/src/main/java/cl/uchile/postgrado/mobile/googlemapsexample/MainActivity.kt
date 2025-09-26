package cl.uchile.postgrado.mobile.googlemapsexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cl.uchile.postgrado.mobile.googlemapsexample.ui.screens.main.MapsExample
import cl.uchile.postgrado.mobile.googlemapsexample.ui.theme.GoogleMapsExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoogleMapsExampleTheme {
                MapsExample()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapsExamplePreview() {
    GoogleMapsExampleTheme {
        MapsExample()
    }
}