package cl.uchile.postgrado.mobile.shoppinglist.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val BackPurple80 = Color(0xFF000000)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val BackPurple40 = Color(0xFFFFFFFF)

val DarkColorScheme = darkColorScheme(
    background = BackPurple40,
    primary = Purple40,
    onPrimary = Color.White,
    secondary = PurpleGrey40,
    onSecondary = Color.White,
    tertiary = Pink40
)

val LightColorScheme = lightColorScheme(
    background = BackPurple80,
    primary = Purple80,
    onPrimary = Color.Black,
    secondary = PurpleGrey80,
    onSecondary = Color.Black,
    tertiary = Pink80

    /* Other default colors to override
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)