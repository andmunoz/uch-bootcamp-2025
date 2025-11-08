package cl.uchile.posgrado.bootcamps.mobile.kmpnativo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cl.uchile.posgrado.bootcamps.mobile.kmpnativo.data.DriverFactory
import cl.uchile.posgrado.bootcamps.mobile.kmpnativo.data.UserRepository
import cl.uchile.posgrado.bootcamps.mobile.kmpnativo.model.AndroidBatteryLevel
import cl.uchile.posgrado.bootcamps.mobile.kmpnativo.ui.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val repo = UserRepository(DriverFactory(this))
        setContent {
            App(repo)
        }
    }
}


/* @Preview
@Composable
fun AppAndroidPreview() {
    App()
} */