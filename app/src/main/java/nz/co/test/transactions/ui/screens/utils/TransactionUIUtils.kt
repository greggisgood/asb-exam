package nz.co.test.transactions.ui.screens.utils

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import nz.co.test.transactions.domain.entity.AmountToDisplay
import nz.co.test.transactions.ui.theme.LocalCustomColorsPalette
import java.math.BigDecimal
import java.text.DecimalFormat

/**
 * Formats the [BigDecimal] value in a correct way to display the amount in the UI
 *
 * @param amountToDisplay The amount that will be displayed in the UI
 * @param debit The debited amount
 * @param credit The credited amount
 *
 * @return The formatted amount as a String
 */
fun formatAmountText(
    amountToDisplay: AmountToDisplay,
    debit: BigDecimal,
    credit: BigDecimal
): String {
    val formatter = DecimalFormat("#,##0.00")
    return when (amountToDisplay) {
        AmountToDisplay.Credit -> "+$${formatter.format(credit)}"
        AmountToDisplay.Debit -> "-$${formatter.format(debit)}"
        AmountToDisplay.Zero -> "$0.00"
    }
}

/**
 * Retrieves the correct Color to use when showing the specific amount in the UI
 *
 * @param amountToDisplay The amount that will be displayed in the UI
 *
 * @return The Color to represent the specific amount
 */
@Composable
fun getAmountTextColor(amountToDisplay: AmountToDisplay) = when (amountToDisplay) {
    AmountToDisplay.Credit -> LocalCustomColorsPalette.current.customCreditColor
    AmountToDisplay.Debit -> LocalCustomColorsPalette.current.customDebitColor
    AmountToDisplay.Zero -> MaterialTheme.colors.onSurface
}