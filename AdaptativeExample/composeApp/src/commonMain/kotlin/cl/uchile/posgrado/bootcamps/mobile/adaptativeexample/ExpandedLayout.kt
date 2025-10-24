package cl.uchile.posgrado.bootcamps.mobile.adaptativeexample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExpandedLayout(products: List<String>) {
    FlowRow (
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        products.forEach { product ->
            ProductCard(name = product, Modifier.width(250.dp))
        }
    }
}