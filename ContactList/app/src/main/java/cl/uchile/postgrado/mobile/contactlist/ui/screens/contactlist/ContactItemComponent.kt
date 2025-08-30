package cl.uchile.postgrado.mobile.contactlist.ui.screens.contactlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.uchile.postgrado.mobile.contactlist.R
import cl.uchile.postgrado.mobile.contactlist.room.Contact

@Composable
fun ContactItemComponent(contact: Contact, navController: NavController) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = CutCornerShape(8.dp),
        border = CardDefaults.outlinedCardBorder(),
        onClick = {
            navController.navigate("edit_contact/${contact.id}")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row() {
                Text(
                    stringResource(R.string.name_label),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    contact.name,
                    modifier = Modifier.weight(2f)
                )
            }
            Row() {
                Text(
                    stringResource(R.string.phone_label),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    contact.phoneNumber,
                    modifier = Modifier.weight(2f)
                )
            }
            Row() {
                Text(
                    stringResource(R.string.email_label),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    contact.email,
                    modifier = Modifier.weight(2f)
                )
            }
        }
    }
}