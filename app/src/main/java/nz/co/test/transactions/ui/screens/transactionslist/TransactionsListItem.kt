package nz.co.test.transactions.ui.screens.transactionslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import nz.co.test.transactions.domain.entity.Transaction
import nz.co.test.transactions.ui.screens.utils.formatAmountToDisplayText
import nz.co.test.transactions.ui.screens.utils.formatTransactionDateText
import nz.co.test.transactions.ui.screens.utils.getAmountTextColor
import nz.co.test.transactions.ui.theme.ApplicationTheme
import nz.co.test.transactions.ui.theme.CombinedThemePreviews
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Composable that represents a single item in the Transactions list
 */
@Composable
fun TransactionsListItem(
    modifier: Modifier = Modifier,
    transaction: Transaction,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag(TRANSACTIONS_LIST_ITEM_CARD),
    ) {
        Row(
            modifier = modifier
                .padding(16.dp),
        ) {
            Column(
                modifier = modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    modifier = modifier.testTag(TRANSACTIONS_LIST_ITEM_SUMMARY),
                    text = transaction.summary,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Start,
                )
                Text(
                    modifier = modifier.testTag(TRANSACTIONS_LIST_ITEM_TRANSACTION_DATE),
                    text = formatTransactionDateText(transaction.transactionDate),
                    style = MaterialTheme.typography.subtitle2,
                    textAlign = TextAlign.Start,
                )
            }
            Spacer(modifier = modifier.width(8.dp))

            val debitAmount = transaction.debit
            val creditAmount = transaction.credit
            val amountToDisplay = transaction.amountToDisplay

            Text(
                modifier = modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .testTag(TRANSACTIONS_LIST_ITEM_AMOUNT),
                text = formatAmountToDisplayText(
                    amountToDisplay = amountToDisplay,
                    debit = debitAmount,
                    credit = creditAmount,
                ),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.body1,
                color = getAmountTextColor(amountToDisplay),
            )
        }
    }
}

/**
 * A Preview Composable for [TransactionsListItem] showing the debited amount
 */
@CombinedThemePreviews
@Composable
private fun TransactionsListItemDebitPreview() {
    val transaction = Transaction(
        id = 1,
        summary = "Test Summary",
        transactionDate = LocalDateTime.of(2021, 8, 31, 15, 47, 10, 0),
        debit = BigDecimal(9379.55),
        credit = BigDecimal(0),
    )
    ApplicationTheme {
        TransactionsListItem(
            transaction = transaction,
            onClick = {},
        )
    }
}

/**
 * A Preview Composable for [TransactionsListItem] showing the credited amount
 */
@CombinedThemePreviews
@Composable
private fun TransactionsListItemCreditPreview() {
    val transaction = Transaction(
        id = 1,
        summary = "Test Summary",
        transactionDate = LocalDateTime.of(2021, 8, 31, 15, 47, 10, 0),
        debit = BigDecimal(0),
        credit = BigDecimal(9379.55),
    )
    ApplicationTheme {
        TransactionsListItem(
            transaction = transaction,
            onClick = {},
        )
    }
}

/**
 * A Preview Composable for [TransactionsListItem] showing a zero amount due to both debit and
 * credit values existing (which is improbable in this exercise)
 */
@CombinedThemePreviews
@Composable
private fun TransactionsListItemZeroAmountPreview() {
    val transaction = Transaction(
        id = 1,
        summary = "Test Summary",
        transactionDate = LocalDateTime.of(2021, 8, 31, 15, 47, 10, 0),
        debit = BigDecimal(9379.55),
        credit = BigDecimal(9379.55),
    )
    ApplicationTheme {
        TransactionsListItem(
            transaction = transaction,
            onClick = {},
        )
    }
}

const val TRANSACTIONS_LIST_ITEM_CARD = "transactions_list_item:card"
const val TRANSACTIONS_LIST_ITEM_SUMMARY = "transactions_list_item:text_summary"
const val TRANSACTIONS_LIST_ITEM_TRANSACTION_DATE = "transactions_list_item:text_transaction_date"
const val TRANSACTIONS_LIST_ITEM_AMOUNT = "transactions_list_item:text_amount"