package nz.co.test.transactions.ui.screens.transactionslist

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import nz.co.test.transactions.domain.entity.Transaction
import org.junit.Rule
import org.junit.Test
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Test class for [TransactionsListItem]
 */
class TransactionsListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupComposable(
        id: Int = 1,
        transactionDate: LocalDateTime = LocalDateTime.of(2022, 2, 17, 10, 44, 35, 0),
        summary: String = "Test Summary",
        debit: BigDecimal = BigDecimal.valueOf(3461.35),
        credit: BigDecimal = BigDecimal.valueOf(0),
        onClick: () -> Unit = {},
    ) {
        val transaction = Transaction(
            id = id,
            transactionDate = transactionDate,
            summary = summary,
            debit = debit,
            credit = credit,
        )

        composeTestRule.setContent {
            TransactionsListItem(
                transaction = transaction,
                onClick = onClick,
            )
        }
    }

    @Test
    fun testThatTheSummaryIsShown() {
        val expectedSummary = "Expected Summary"
        setupComposable(summary = expectedSummary)

        composeTestRule.onNodeWithTag(TRANSACTIONS_LIST_ITEM_SUMMARY, useUnmergedTree = true)
            .assertTextEquals(expectedSummary)
    }

    @Test
    fun testThatTheTransactionDateIsShownInTheCorrectFormat() {
        // Should be displayed as 29 Oct 2024, 5:30 PM
        setupComposable(transactionDate = LocalDateTime.of(2024, 10, 29, 17, 30, 0, 0))

        composeTestRule.onNodeWithTag(TRANSACTIONS_LIST_ITEM_TRANSACTION_DATE, useUnmergedTree = true)
            .assertTextEquals("29 Oct 2024, 5:30 PM")
    }

    @Test
    fun testThatTheDebitAmountIsShownWithTheCorrectFormat() {
        // Should be displayed as -$6,500.50
        setupComposable(debit = BigDecimal(6500.50), credit = BigDecimal(0.00))

        composeTestRule.onNodeWithTag(TRANSACTIONS_LIST_ITEM_AMOUNT, useUnmergedTree = true)
            .assertTextEquals("-$6,500.50")
    }

    @Test
    fun testThatTheDebitAmountIsRoundedOffAndShownWithTheCorrectFormat() {
        // Should be displayed as -$6,500.60
        setupComposable(debit = BigDecimal(6500.5982), credit = BigDecimal(0.00))

        composeTestRule.onNodeWithTag(TRANSACTIONS_LIST_ITEM_AMOUNT, useUnmergedTree = true)
            .assertTextEquals("-$6,500.60")
    }

    @Test
    fun testThatTheCreditAmountIsShownWithTheCorrectFormat() {
        // Should be displayed as +$6,500.50
        setupComposable(debit = BigDecimal(0.00), credit = BigDecimal(6500.50))

        composeTestRule.onNodeWithTag(TRANSACTIONS_LIST_ITEM_AMOUNT, useUnmergedTree = true)
            .assertTextEquals("+$6,500.50")
    }

    @Test
    fun testThatTheCreditAmountIsRoundedOffAndShownWithTheCorrectFormat() {
        // Should be displayed as +$6,500.60
        setupComposable(debit = BigDecimal(0.00), credit = BigDecimal(6500.5982))

        composeTestRule.onNodeWithTag(TRANSACTIONS_LIST_ITEM_AMOUNT, useUnmergedTree = true)
            .assertTextEquals("+$6,500.60")
    }

    @Test
    fun testThatClickingTheCardInvokesTheClickCallback() {
        val clicked = mutableStateOf(false)
        setupComposable(onClick = { clicked.value = true })

        composeTestRule.onNodeWithTag(TRANSACTIONS_LIST_ITEM_CARD, useUnmergedTree = true)
            .performClick()

        assert(clicked.value)
    }
}