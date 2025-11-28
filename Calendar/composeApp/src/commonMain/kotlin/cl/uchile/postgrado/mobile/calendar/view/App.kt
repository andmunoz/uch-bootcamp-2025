package cl.uchile.postgrado.mobile.calendar.view

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import cl.uchile.postgrado.mobile.calendar.view.layouts.CompactLayout
import cl.uchile.postgrado.mobile.calendar.view.layouts.ExpandedLayout
import cl.uchile.postgrado.mobile.calendar.view.layouts.MediumLayout
import cl.uchile.postgrado.mobile.calendar.view.tools.WindowSize
import cl.uchile.postgrado.mobile.calendar.view.tools.getWindowSizeClass
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        BoxWithConstraints {
            val width = this.maxWidth
            when(getWindowSizeClass(width)) {
                WindowSize.COMPACT -> ExpandedLayout(1, 1)
                WindowSize.MEDIUM -> ExpandedLayout(4, 2)
                WindowSize.EXPANDED -> ExpandedLayout(6, 3)
/*              WindowSize.COMPACT -> CompactLayout()
                WindowSize.MEDIUM -> MediumLayout()
                WindowSize.EXPANDED -> ExpandedLayout() */
            }
        }
    }
}