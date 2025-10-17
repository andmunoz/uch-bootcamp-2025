package cl.uchile.posgrado.bootcamps.mobile.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cl.uchile.posgrado.bootcamps.mobile.demo.ui.AppMobile

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AppMobile()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    AppMobile()
}