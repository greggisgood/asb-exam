package nz.co.test.transactions.ui.screens.utils

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import nz.co.test.transactions.domain.entity.AmountToDisplay
import nz.co.test.transactions.ui.theme.LocalCustomColorsPalette
import java.math.BigDecimal
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Formats the [LocalDateTime] to an appropriate Date format to be displayed in the UI
 *
 * @param transactionDate The Transaction date
 *
 * @return A String containing the formatted date
 */
fun formatTransactionDateText(transactionDate: LocalDateTime): String = transactionDate.format(
    DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a")
)

/**
 * Formats the amount to display in a correct way to be displayed in the UI
 *
 * @param amountToDisplay The amount that will be displayed in the UI
 * @param debit The debited amount
 * @param credit The credited amount
 *
 * @return A String containing the formatted amount
 */
fun formatAmountToDisplayText(
    amountToDisplay: AmountToDisplay,
    debit: BigDecimal,
    credit: BigDecimal
): String = when (amountToDisplay) {
    AmountToDisplay.Credit -> "+${formatAmountText(credit)}"
    AmountToDisplay.Debit -> "-${formatAmountText(debit)}"
    AmountToDisplay.Zero -> formatAmountText(BigDecimal.ZERO)
}

/**
 * Formats the given amount with a specified [DecimalFormat] to be displayed in the UI
 *
 * @param amount The amount to be formatted
 *
 * @return A String containing the formatted amount
 */
fun formatAmountText(amount: BigDecimal): String {
    val formatter = DecimalFormat("#,##0.00")
    return "$${formatter.format(amount)}"
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