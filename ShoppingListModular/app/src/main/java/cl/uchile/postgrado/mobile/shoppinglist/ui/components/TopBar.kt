package cl.uchile.postgrado.mobile.shoppinglist.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cl.uchile.postgrado.mobile.shoppinglist.MainActivity
import cl.uchile.postgrado.mobile.shoppinglist.R

@SuppressLint("ViewModelConstructorInComposable")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListTopBar(text: String) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    val userSettingsViewModel = MainActivity.userSettingsViewModel
    var selectedTheme by remember { mutableStateOf(userSettingsViewModel.theme) }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = text,
                modifier = Modifier.padding(start = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            IconButton(onClick = {
                /* Open Account Modal */
            }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Account"
                )
            }
        },
        actions = {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Localized description"
                )
            }
            ThemeSettingDialog(
                showDialog = showDialog,
                title = "Theme Settings",
                onDismiss = { showDialog = false },
                currentTheme = selectedTheme,
                onThemeChange = { theme ->
                    selectedTheme = theme
                    userSettingsViewModel.theme = theme
                    userSettingsViewModel.saveThemeSetting(MainActivity())
                    showDialog = false
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Theme Settings") },
                    onClick = { showDialog = true },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = null
                        )
                    }
                )
                DropdownMenuItem(
                    text = { Text("Language Settings") },
                    onClick = {  },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.outline_language_24),
                            contentDescription = null
                        )
                    }
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}
