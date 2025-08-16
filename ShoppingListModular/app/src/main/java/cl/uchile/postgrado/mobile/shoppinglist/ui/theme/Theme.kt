package cl.uchile.postgrado.mobile.shoppinglist.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun DefaultTheme(
    theme: String,
    content: @Composable () -> Unit
) {
    val colorScheme = when(theme) {
        "light" -> LightColorScheme
        "dark" -> DarkColorScheme
        else ->
            if (isSystemInDarkTheme())
                DarkColorScheme
            else
                LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}