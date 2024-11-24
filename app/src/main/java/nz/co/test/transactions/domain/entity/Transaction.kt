package nz.co.test.transactions.domain.entity

import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Data class that represents each transaction made by the User
 */
data class Transaction(
    val id: Int,
    val transactionDate: LocalDateTime,
    val summary: String,
    val debit: BigDecimal,
    val credit: BigDecimal
) {

    val amountToDisplay = when {
        debit.signum() == 0 -> AmountToDisplay.Credit
        credit.signum() == 0 -> AmountToDisplay.Debit
        // For this exercise, it is impossible for both credit and debit amounts to exist. When
        // that happens, just display a zero amount
        else -> AmountToDisplay.Zero
    }

    val gstAmount: BigDecimal = when (amountToDisplay) {
        AmountToDisplay.Credit -> credit.multiply(BigDecimal(GST_RATE))
        AmountToDisplay.Debit -> debit.multiply(BigDecimal(GST_RATE))
        AmountToDisplay.Zero -> BigDecimal.ZERO
    }

    companion object {
        private const val GST_RATE = 0.15
    }
}