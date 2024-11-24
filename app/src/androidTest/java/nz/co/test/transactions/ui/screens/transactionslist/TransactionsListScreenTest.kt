package nz.co.test.transactions.ui.screens.transactionslist

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import nz.co.test.transactions.domain.entity.Transaction
import org.junit.Rule
import org.junit.Test
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Test class for [TransactionsListScreen]
 */
class TransactionsListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val sampleTransaction = Transaction(
        id = 1,
        transactionDate = LocalDateTime.of(2022, 2, 17, 10, 44, 35, 0),
        summary = "Test Summary",
        debit = BigDecimal.valueOf(3461.35),
        credit = BigDecimal.valueOf(0),
    )

    @Test
    fun testThatTheListOfTransactionsAreShown() {
        val transactionTwo = sampleTransaction.copy(id = 2)
        val transactionThree = sampleTransaction.copy(id = 3)

        composeTestRule.setContent {
            TransactionsListScreen(
                savedScrollPosition = 0,
                transactions = listOf(sampleTransaction, transactionTwo, transactionThree),
                onScrollPositionChanged = {},
                onTransactionClick = {},
            )
        }

        composeTestRule.onNodeWithTag("${TRANSACTIONS_LIST_SCREEN_ENTRY}_1").assertExists()
        composeTestRule.onNodeWithTag("${TRANSACTIONS_LIST_SCREEN_ENTRY}_2").assertExists()
        composeTestRule.onNodeWithTag("${TRANSACTIONS_LIST_SCREEN_ENTRY}_3").assertExists()
    }

    @Test
    fun testThatClickingATransactionInvokesTheClickCallback() {
        val clicked = mutableStateOf(false)
        composeTestRule.setContent {
            TransactionsListScreen(
                savedScrollPosition = 0,
                transactions = listOf(sampleTransaction),
                onScrollPositionChanged = {},
                onTransactionClick = { clicked.value = true },
            )
        }

        composeTestRule.onNodeWithTag("${TRANSACTIONS_LIST_SCREEN_ENTRY}_1")
            .performClick()

        assert(clicked.value)
    }
}