package cl.uchile.postgrado.mobile.catalogo.ui.catalog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.uchile.postgrado.mobile.catalogo.model.data.Product

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            Text(
                text = product.nombre,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = product.descripcion,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = product.precio.toString(),
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}