package cl.uchile.postgrado.mobile.catalogo.ui.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cl.uchile.postgrado.mobile.catalogo.model.repository.CatalogFirestoreRepository
import cl.uchile.postgrado.mobile.catalogo.model.repository.ImageStorageRepository
import cl.uchile.postgrado.mobile.catalogo.viewmodel.CatalogViewModel
import cl.uchile.postgrado.mobile.catalogo.viewmodel.CatalogViewModelFactory

@Composable
fun CatalogComponent(modifier: Modifier = Modifier, navController: NavController) {
    val catalogViewModel: CatalogViewModel = viewModel(
        factory = CatalogViewModelFactory(
            productRepository = CatalogFirestoreRepository(),
            imageStorageRepository = ImageStorageRepository()
        )
    )
    val products by catalogViewModel.products.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(products.size) { index ->
            val product = products[index]
            ProductCard(product = product)
        }
    }
}