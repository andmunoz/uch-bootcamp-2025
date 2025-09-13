package cl.uchile.postgrado.mobile.catalogo.ui.addproduct

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cl.uchile.postgrado.mobile.catalogo.R
import cl.uchile.postgrado.mobile.catalogo.model.data.Product
import cl.uchile.postgrado.mobile.catalogo.model.repository.CatalogFirestoreRepository
import cl.uchile.postgrado.mobile.catalogo.model.repository.ImageStorageRepository
import cl.uchile.postgrado.mobile.catalogo.viewmodel.CatalogViewModel
import cl.uchile.postgrado.mobile.catalogo.viewmodel.CatalogViewModelFactory
import java.lang.Double.parseDouble

@Composable
fun AddProductForm(modifier: Modifier, navController: NavController) {
    var product by remember { mutableStateOf(Product()) }
    val catalogViewModel: CatalogViewModel = viewModel(
        factory = CatalogViewModelFactory(
            productRepository = CatalogFirestoreRepository(),
            imageStorageRepository = ImageStorageRepository()
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(stringResource(id = R.string.product_id))
        TextField(
            value = product.id,
            onValueChange = { product.id = it }
        )

        Text(stringResource(id = R.string.product_name))
        TextField(
            value = product.nombre,
            onValueChange = { product.nombre = it }
        )

        Text(stringResource(id = R.string.product_description))
        TextField(
            value = product.descripcion,
            onValueChange = { product.descripcion = it }
        )

        Text(stringResource(id = R.string.product_price))
        TextField(
            value = product.precio.toString(),
            onValueChange = { product.precio = parseDouble(it) }
        )

        Text(stringResource(id = R.string.product_image_url))
        TextField(
            value = product.imagenUrl,
            onValueChange = { product.imagenUrl = it }
        )

        Button(
            onClick = {
                catalogViewModel.addProduct(product)
                navController.navigate("catalog")
            }
        ) {
            Text(stringResource(id = R.string.add_product))
        }
    }
}