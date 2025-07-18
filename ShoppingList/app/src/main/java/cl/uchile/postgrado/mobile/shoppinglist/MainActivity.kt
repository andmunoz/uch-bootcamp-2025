package cl.uchile.postgrado.mobile.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.uchile.postgrado.mobile.shoppinglist.ui.theme.ShoppingListTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "product_list") {
        composable("product_list") { AppFrame(navController) }
        composable("product_detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            AppDetail(id, navController)
        }
    }
}

@Composable
fun AppDetail(id: String?, navController: NavHostController) {
    Column() {
        Text("Detalle del Producto")
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Volver")
        }
    }
}

@Composable
fun NavigationMenu(drawerState: DrawerState, scope: CoroutineScope) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.LightGray)
    ) {
        Text(
            text = "Menú",
            modifier = Modifier.padding(bottom = 12.dp)
                .clickable { scope.launch { drawerState.close() } }
        )
        HorizontalDivider()
        Text(
            text = "Opción 1",
            modifier = Modifier.clickable { /* To Do */ }
        )
        Text(
            text = "Opción 2",
            modifier = Modifier.clickable { /* To Do */ }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(drawerState: DrawerState, scope: CoroutineScope) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Mi Lista de Compras",
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            /* Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menú",
                modifier = Modifier.clickable { scope.launch { drawerState.open() } }
            ) */
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.Black
        )
    )
}

@Composable
fun BottomBar() {
    BottomAppBar(
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
}

@Composable
fun FAButton() {
    ExtendedFloatingActionButton(
        containerColor = Color.Blue,
        contentColor = Color.White,
        onClick = { /*TODO*/ }
    ) {
        Icon(
            imageVector = Icons.Sharp.Add,
            contentDescription = "Agregar",
            tint = Color.Yellow
        )
        Text(
            text = "Agregar",
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppFrame(navController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope: CoroutineScope = rememberCoroutineScope()

    /* ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
           NavigationMenu(drawerState, scope)
        }
    )
    {*/
    Scaffold(
        topBar = {
            TopBar(drawerState, scope)
        },
        /* bottomBar = {
            BottomBar()
        }, */
        floatingActionButton = {
            FAButton()
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        ShoppingList(
            modifier = Modifier.padding(innerPadding),
            navController
        )
    }
    // }
}

@Composable
fun ShoppingList(modifier: Modifier = Modifier, navController: NavHostController) {
    val productos = listOf("Leche", "Huevos", "Spaguetti", "Arroz", "Comida para Perros")
    var seleccionado by remember { mutableStateOf(false) }

    Image(
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = "Fondo de Pantalla",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )

    Column() {
        Spacer( modifier = Modifier.weight(1f) )
        Image(
            painter = painterResource(R.mipmap.ic_billy),
            contentDescription = "Android de Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp, bottom = 80.dp)
    ) {
        items(productos) {producto ->
            Card(
                elevation = CardDefaults.cardElevation(4.dp),
                shape = CutCornerShape(8.dp),
                border = CardDefaults.outlinedCardBorder(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = seleccionado,
                        onCheckedChange = { seleccionado = it } /* ,
                        modifier = Modifier.padding(end = 8.dp)*/
                    )
                    Text(
                        producto,
                        modifier = Modifier.weight(1f)
                    )
                    Button(
                        onClick = {
                            navController.navigate("product_detail/1")
                        },
                        modifier = Modifier.padding(start = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Blue
                        ),
                        shape = ButtonDefaults.elevatedShape
                    ) {
                        Text("Detalles")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShoppingListPreview() {
    ShoppingListTheme {
        AppNavigation()
    }
}