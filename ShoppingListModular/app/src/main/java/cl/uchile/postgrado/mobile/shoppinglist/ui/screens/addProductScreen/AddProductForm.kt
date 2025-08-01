package cl.uchile.postgrado.mobile.shoppinglist.ui.screens.addProductScreen

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import cl.uchile.postgrado.mobile.shoppinglist.R
import cl.uchile.postgrado.mobile.shoppinglist.ui.components.PrimaryButton
import cl.uchile.postgrado.mobile.shoppinglist.ui.components.SecondaryButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Componente que muestra el detalle del producto
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductForm(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    viewModel: AddProductViewModel = viewModel()
) {

    val productCategories = listOf("Abarrotes", "Lácteos", "Limpieza", "Hogar")
    var expanded by remember { mutableStateOf(false) }
    var visible: Boolean by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

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
        modifier = Modifier
            .padding(top = 100.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)
            .imePadding(),
    ) {
        Text(
            text = stringResource(R.string.add_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        TextField(
            value = viewModel.productName,
            onValueChange = { viewModel.onProductNameChange(it) },
            label = { stringResource(R.string.product_label) },
            placeholder = { Text(stringResource(R.string.product_hint)) },
            isError = viewModel.productNameError != null,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        AnimatedVisibility(visible = viewModel.productNameError != null) {
            Text(
                text = viewModel.productNameError ?: "",
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        TextField(
            value = viewModel.productBrand,
            onValueChange = { viewModel.onProductBrandChange(it) },
            label = { Text("Marca") },
            placeholder = { Text("Marca del Producto") },
            isError = viewModel.productBrandError != null,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        AnimatedVisibility(visible = viewModel.productBrandError != null) {
            Text(
                text = viewModel.productBrandError?: "",
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        TextField(
            value = viewModel.productDescription,
            onValueChange = { viewModel.onProductDescriptionChange(it) },
            label = { Text("Descripción") },
            placeholder = { Text("Descripción del Producto") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextField(
                value = viewModel.productCategory,
                onValueChange = { },
                readOnly = true,
                label = { Text("Categoría") },
                trailingIcon = { TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryEditable, true) // Importante para que funcione correctamente
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                productCategories.forEach { opt ->
                    DropdownMenuItem(
                        text = { Text(opt) },
                        onClick = {
                            viewModel.onProductCategoryChange(opt)
                            expanded = false
                        }
                    )
                }
            }
        }

        TextField(
            value = viewModel.productPrice,
            onValueChange = { viewModel.onProductPriceChange(it) },
            label = { Text("Precio") },
            placeholder = { Text("Precio del Producto") },
            singleLine = true,
            isError = viewModel.productPriceError != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        AnimatedVisibility(visible = viewModel.productPriceError != null) {
            Text(
                text = viewModel.productPriceError?: "",
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

        Row {
            SecondaryButton(
                text = stringResource(R.string.cancel_button),
                onClick = { navController.popBackStack() }
            )
            PrimaryButton(
                text = stringResource(R.string.save_button),
                onClick = {
                    viewModel.validateForm()
                    if (viewModel.isFormValid) {
                        viewModel.addProduct(navController.previousBackStackEntry?.savedStateHandle)
                        scope.launch {
                            val result = snackbarHostState
                                .showSnackbar(
                                    "¿Está seguro de agregar el producto?",
                                    actionLabel = "Sí",
                                    withDismissAction = true,
                                    duration = SnackbarDuration.Indefinite
                                )
                            when (result){
                                SnackbarResult.ActionPerformed -> {
                                    navController.popBackStack()
                                    Toast.makeText(
                                        navController.context,
                                        "¡Producto agregado a la lista!",
                                        Toast.LENGTH_SHORT).show()
                                }
                                SnackbarResult.Dismissed -> {
                                    Toast.makeText(
                                        navController.context,
                                        "¡Acción cancelada!",
                                        Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
            )
        }
    }
    /* scope.launch {
        snackbarHostState.showSnackbar("¡Producto agregado a la lista!")
    }
    navController.popBackStack() */
}