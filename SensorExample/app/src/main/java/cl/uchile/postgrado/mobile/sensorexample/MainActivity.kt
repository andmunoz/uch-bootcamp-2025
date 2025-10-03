package cl.uchile.postgrado.mobile.sensorexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cl.uchile.postgrado.mobile.sensorexample.ui.SensorView
import cl.uchile.postgrado.mobile.sensorexample.ui.theme.SensorExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SensorView()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    SensorExampleTheme {
        SensorView()
    }
}