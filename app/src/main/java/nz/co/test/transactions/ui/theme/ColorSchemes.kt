package nz.co.test.transactions.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

// Light Color Scheme
val lightColorPalette = lightColors(
    primary = LightPrimary,
    primaryVariant = LightPrimaryVariant,
    secondary = LightSecondary,
    background = LightBackground,
    surface = LightSurface,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,

    onSurface = Color.Black
)

// Dark Color Scheme
val darkColorPalette = darkColors(
    primary = DarkPrimary,
    primaryVariant = DarkPrimaryVariant,
    secondary = DarkSecondary,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

// Custom Light Color Scheme
val customLightColorPalette = CustomColorsPalette(
    customDebitColor = LightRed,
    customCreditColor = LightGreen,
)

// Custom Dark Color Scheme
val customDarkColorPalette = CustomColorsPalette(
    customDebitColor = DarkRed,
    customCreditColor = DarkGreen,
)