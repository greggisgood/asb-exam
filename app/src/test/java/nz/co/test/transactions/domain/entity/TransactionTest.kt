package nz.co.test.transactions.domain.entity

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Test class for [Transaction]
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransactionTest {

    private lateinit var underTest: Transaction

    private val transactionId = 1
    private val transactionDate = LocalDateTime.of(2022, 2, 17, 10, 44, 35, 0)
    private val transactionSummary = "Test Summary"

    @Test
    fun `test that the amount to display is credit when the debit amount is empty`() = runTest {
        underTest = Transaction(
            id = transactionId,
            transactionDate = transactionDate,
            summary = transactionSummary,
            debit = BigDecimal(0.00),
            credit = BigDecimal(3461.35),
        )

        assertThat(underTest.amountToDisplay).isEqualTo(AmountToDisplay.Credit)
    }

    @Test
    fun `test that the amount to display is debit when the credit amount is empty`() = runTest {
        underTest = Transaction(
            id = transactionId,
            transactionDate = transactionDate,
            summary = transactionSummary,
            debit = BigDecimal(3461.35),
            credit = BigDecimal(0.00),
        )

        assertThat(underTest.amountToDisplay).isEqualTo(AmountToDisplay.Debit)
    }

    @Test
    fun `test that the amount to display is zero when both credit and debit amounts exist`() =
        runTest {
            underTest = Transaction(
                id = transactionId,
                transactionDate = transactionDate,
                summary = transactionSummary,
                debit = BigDecimal(3461.35),
                credit = BigDecimal(3461.35),
            )

            assertThat(underTest.amountToDisplay).isEqualTo(AmountToDisplay.Zero)
        }
}