package nz.co.test.transactions.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Data class that provides custom Colors outside the Theme colors
 */
@Immutable
data class CustomColorsPalette(
    val customDebitColor: Color = Color.Unspecified,
    val customCreditColor: Color = Color.Unspecified,
)

val LocalCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }
