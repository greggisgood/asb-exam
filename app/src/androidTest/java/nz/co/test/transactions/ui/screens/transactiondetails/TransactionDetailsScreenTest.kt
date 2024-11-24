package nz.co.test.transactions.ui.screens.transactiondetails

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import nz.co.test.transactions.domain.entity.Transaction
import org.junit.Rule
import org.junit.Test
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Test class for [TransactionDetailsScreen]
 */
class TransactionDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupComposable(
        id: Int = 1,
        transactionDate: LocalDateTime = LocalDateTime.of(2022, 2, 17, 10, 44, 35, 0),
        summary: String = "Test Summary",
        debit: BigDecimal = BigDecimal.valueOf(3461.35),
        credit: BigDecimal = BigDecimal.valueOf(0),
    ) {
        val transaction = Transaction(
            id = id,
            transactionDate = transactionDate,
            summary = summary,
            debit = debit,
            credit = credit,
        )

        composeTestRule.setContent {
            TransactionDetailsScreen(
                transaction = transaction,
            )
        }
    }

    @Test
    fun testThatNoContentIsDisplayedWhenTransactionIsNull() {
        composeTestRule.setContent {
            TransactionDetailsScreen(transaction = null)
        }

        composeTestRule.onNodeWithTag(TRANSACTION_DETAILS_SCREEN_SUMMARY).assertDoesNotExist()
        composeTestRule.onNodeWithTag(TRANSACTION_DETAILS_SCREEN_AMOUNT).assertDoesNotExist()
        composeTestRule.onNodeWithTag(TRANSACTION_DETAILS_SCREEN_TRANSACTION_DATE)
            .assertDoesNotExist()
        composeTestRule.onNodeWithTag(TRANSACTION_DETAILS_SCREEN_GST).assertDoesNotExist()
    }

    @Test
    fun testThatTheContentIsDisplayedWhenTransactionExists() {
        setupComposable()

        composeTestRule.onNodeWithTag(TRANSACTION_DETAILS_SCREEN_SUMMARY).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TRANSACTION_DETAILS_SCREEN_AMOUNT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TRANSACTION_DETAILS_SCREEN_TRANSACTION_DATE)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(TRANSACTION_DETAILS_SCREEN_GST).assertIsDisplayed()
    }

    @Test
    fun testThatTheSummaryIsShown() {
        val expectedSummary = "Expected Summary"
        setupComposable(summary = expectedSummary)

        composeTestRule.onNodeWithTag(TRANSACTION_DETAILS_SCREEN_SUMMARY)
            .assertTextEquals(expectedSummary)
    }

    @Test
    fun testThatTheDebitAmountIsShownWithTheCorrectFormat() {
        // Should be displayed as -$6,500.50
        setupComposable(debit = BigDecimal(6500.50), credit = BigDecimal(0.00))

        composeTestRule.onNodeWithTag(TRANSACTION_DETAILS_SCREEN_AMOUNT)
            .assertTextEquals("-$6,500.50")
    }

    @Test
    fun testThatTheDebitAmountIsRoundedOffAndShownWithTheCorrectFormat() {
        // Should be displayed as -$6,500.60
        setupComposable(debit = BigDecimal(6500.5982), credit = BigDecimal(0.00))

        composeTestRule.onNodeWithTag(TRANSACTION_DETAILS_SCREEN_AMOUNT)
            .assertTextEquals("-$6,500.60")
    }

    @Test
    fun testThatTheGstAmountFromTheDebitAmountIsShownWithTheCorrectFormat() {
        // The GST amount from the debit amount should be displayed as GST: $975.09
        setupComposable(debit = BigDecimal(6500.5982), credit = BigDecimal(0.00))

        composeTestRule.onNodeWithTag(TRANSACTION_DETAILS_SCREEN_GST)
            .assertTextEquals("GST: $975.09")
    }

    @Test
    fun testThatTheCreditAmountIsShownWithTheCorrectFormat() {
        // Should be displayed as +$6,500.50
        setupComposable(debit = BigDecimal(0.00), credit = BigDecimal(6500.50))

        composeTestRule.onNodeWithTag(TRANSACTION_DETAILS_SCREEN_AMOUNT)
            .assertTextEquals("+$6,500.50")
    }

    @Test
    fun testThatTheCreditAmountIsRoundedOffAndShownWithTheCorrectFormat() {
        // Should be displayed as +$6,500.60
        setupComposable(debit = BigDecimal(0.00), credit = BigDecimal(6500.5982))

        composeTestRule.onNodeWithTag(TRANSACTION_DETAILS_SCREEN_AMOUNT)
            .assertTextEquals("+$6,500.60")
    }

    @Test
    fun testThatTheGstAmountFromTheCreditAmountIsShownWithTheCorrectFormat() {
        // The GST amount from the credit amount should be displayed as GST: $975.09
        setupComposable(debit = BigDecimal(0.00), credit = BigDecimal(6500.5982))

        composeTestRule.onNodeWithTag(TRANSACTION_DETAILS_SCREEN_GST)
            .assertTextEquals("GST: $975.09")
    }

    @Test
    fun testThatTheTransactionDateIsShownInTheCorrectFormat() {
        // Should be displayed as 29 Oct 2024, 5:30 PM
        setupComposable(transactionDate = LocalDateTime.of(2024, 10, 29, 17, 30, 0, 0))

        composeTestRule.onNodeWithTag(TRANSACTION_DETAILS_SCREEN_TRANSACTION_DATE)
            .assertTextEquals("29 Oct 2024, 5:30 PM")
    }
}