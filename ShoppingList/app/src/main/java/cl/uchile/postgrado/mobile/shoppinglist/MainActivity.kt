package cl.uchile.postgrado.mobile.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.uchile.postgrado.mobile.shoppinglist.ui.theme.ShoppingListTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListTheme {
                AppFrame()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppFrame() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color.LightGray)
            ) {
                Text(
                    text = "Menú",
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                HorizontalDivider()
                Text(
                    text = "Opción 1",
                    modifier = Modifier.clickable { scope.launch { drawerState.close() }}
                )
            }
        }
    )
    {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Lista de Compras") },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Inicio"
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.LightGray,
                        titleContentColor = Color.Black,
                        navigationIconContentColor = Color.Black
                    )
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black,
                    tonalElevation = 10.dp,
                    windowInsets = BottomAppBarDefaults.windowInsets
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Inicio",
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Text(
                                text = "Inicio",
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Email,
                                contentDescription = "Correo",
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Text(
                                text = "Correo",
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "Cuenta",
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Text(
                                text = "Cuenta",
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    containerColor = Color.Red,
                    contentColor = Color.White,
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Agregar"
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            ShoppingList(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun ShoppingList(modifier: Modifier = Modifier) {
    val items = listOf("Item 1", "Item 2", "Item 3")
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp, bottom = 80.dp)
    ) {
        items(items) {item ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = false,
                    onCheckedChange = { /*TODO*/ }
                )
                Text(item)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingListPreview() {
    ShoppingListTheme {
        ShoppingList()
    }
}