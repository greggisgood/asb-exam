package nz.co.test.transactions.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

/**
 * The default Theme to use in the app
 */
@Composable
fun ApplicationTheme(
    content: @Composable () -> Unit,
) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val colors = if (isSystemInDarkTheme) darkColorPalette else lightColorPalette
    val customColorsPalette =
        if (isSystemInDarkTheme) customDarkColorPalette else customLightColorPalette

    CompositionLocalProvider(
        LocalCustomColorsPalette provides customColorsPalette
    ) {
        MaterialTheme(
            colors = colors,
            typography = MaterialTheme.typography,
            shapes = MaterialTheme.shapes,
            content = content,
        )
    }
}