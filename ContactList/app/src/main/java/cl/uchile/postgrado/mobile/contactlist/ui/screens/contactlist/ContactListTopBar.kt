package cl.uchile.postgrado.mobile.contactlist.ui.screens.contactlist

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cl.uchile.postgrado.mobile.contactlist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListTopBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        title = {
            Text(text = stringResource(R.string.app_name))
        }
    )
}