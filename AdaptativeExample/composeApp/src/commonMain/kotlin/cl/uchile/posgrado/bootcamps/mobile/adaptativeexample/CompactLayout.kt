package cl.uchile.posgrado.bootcamps.mobile.adaptativeexample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CompactLayout(products: List<String>) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(products.size) { index ->
            ProductCard(name = products[index])
        }
    }
}
