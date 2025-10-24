import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import cl.uchile.posgrado.bootcamps.mobile.adaptativeexample.CompactLayout
import cl.uchile.posgrado.bootcamps.mobile.adaptativeexample.ExpandedLayout
import cl.uchile.posgrado.bootcamps.mobile.adaptativeexample.MediumLayout
import cl.uchile.posgrado.bootcamps.mobile.adaptativeexample.WindowSize
import cl.uchile.posgrado.bootcamps.mobile.adaptativeexample.getWindowSizeClass

@Composable
fun AdaptativeScreen() {
    val products = List(12) { "Product ${it + 1}" }

    BoxWithConstraints {
        val width = this.maxWidth
        when(getWindowSizeClass(width)) {
            WindowSize.COMPACT -> CompactLayout(products)
            WindowSize.MEDIUM -> MediumLayout(products)
            WindowSize.EXPANDED -> ExpandedLayout(products)
        }
    }
}

