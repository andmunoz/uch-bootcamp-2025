package cl.uchile.posgrado.bootcamps.mobile.adaptativeexample

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class WindowSize { COMPACT, MEDIUM, EXPANDED }

@Composable
fun getWindowSizeClass(maxWidth: Dp): WindowSize = when {
    maxWidth < 600.dp -> WindowSize.COMPACT
    maxWidth < 840.dp -> WindowSize.MEDIUM
    else -> WindowSize.EXPANDED
}