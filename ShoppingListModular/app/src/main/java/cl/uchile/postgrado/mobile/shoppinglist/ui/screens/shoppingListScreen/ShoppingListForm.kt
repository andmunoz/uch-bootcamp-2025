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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
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
import cl.uchile.postgrado.mobile.shoppinglist.ui.components.SecondaryButton

// Componente que muestra la lista de productos
@Composable
fun ShoppingListForm(modifier: Modifier = Modifier,
                     navController: NavHostController,
                     productListModel: ShoppingListViewModel = viewModel()) {

    var seleccionado by remember { mutableStateOf(false) }
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    if (savedStateHandle != null) {
        productListModel.addProductFromHandler(savedStateHandle)
    }

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
            painter = painterResource(R.drawable.ic_launcher_foreground),
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
            .padding(top = 100.dp)
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
                        checked = seleccionado,
                        onCheckedChange = { seleccionado = it } /* ,
                        modifier = Modifier.padding(end = 8.dp)*/
                    )
                    Text(
                        producto.productName + " " + producto.productBrand,
                        modifier = Modifier.weight(1f)
                    )
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