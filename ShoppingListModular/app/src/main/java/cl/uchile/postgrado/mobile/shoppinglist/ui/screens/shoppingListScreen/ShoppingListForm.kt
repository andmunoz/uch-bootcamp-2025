package cl.uchile.postgrado.mobile.shoppinglist.ui.screens.shoppingListScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import cl.uchile.postgrado.mobile.shoppinglist.R
import cl.uchile.postgrado.mobile.shoppinglist.model.viewmodel.ShoppingListViewModel
import cl.uchile.postgrado.mobile.shoppinglist.ui.components.SecondaryButton

// Componente que muestra la lista de productos
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListForm(innerPadding: Modifier = Modifier,
                     navController: NavHostController,
                     productListModel: ShoppingListViewModel = viewModel()) {

    var selected by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    if (savedStateHandle != null) {
        productListModel.addProductFromHandler(savedStateHandle)
    }

    Image(
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )

    Column() {
        Spacer( modifier = Modifier.weight(1f) )
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        )
    }

    Column(
        modifier = innerPadding
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextField(
                value = "Supermercado",
                onValueChange = { },
                readOnly = true,
                label = { Text("Lista de Compras") },
                trailingIcon = { TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryEditable, true) // Importante para que funcione correctamente
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                /* productCategories.forEach { opt ->
                    DropdownMenuItem(
                        text = { Text(opt) },
                        onClick = {
                            viewModel.onProductCategoryChange(opt)
                            expanded = false
                        }
                    )
                } */
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(productListModel.products) {producto ->
                Card(
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = CutCornerShape(8.dp),
                    border = CardDefaults.outlinedCardBorder(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White.copy(alpha = 0.5f)     // Transparenta el fondo
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
                            checked = selected,
                            onCheckedChange = { selected = it }
                        )
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                producto.productQuantity.toString() + " " + producto.productName + " " + producto.productBrand
                            )
                            Text(
                                producto.productDescription
                            )
                        }
                        SecondaryButton(
                            text = stringResource(R.string.details_button),
                            onClick = {
                                navController.navigate("product_detail/" + producto.id)
                            }
                        )
                    }
                }
            }
        }
    }
}