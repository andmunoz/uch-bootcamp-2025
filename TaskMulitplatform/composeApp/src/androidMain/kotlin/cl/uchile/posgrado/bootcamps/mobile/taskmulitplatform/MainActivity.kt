package cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.model.database.DatabaseDriverFactory
import cl.uchile.posgrado.bootcamps.mobile.taskmulitplatform.ui.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MainScreen(driverFactory = DatabaseDriverFactory(this))
            }
        }
    }
}
