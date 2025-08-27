package cl.uchile.postgrado.mobile.contactlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cl.uchile.postgrado.mobile.contactlist.ui.components.ContactListNavigation
import cl.uchile.postgrado.mobile.contactlist.ui.theme.ContactListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactListTheme {
                ContactListNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactListPreview() {
    ContactListTheme {
        ContactListNavigation()
    }
}