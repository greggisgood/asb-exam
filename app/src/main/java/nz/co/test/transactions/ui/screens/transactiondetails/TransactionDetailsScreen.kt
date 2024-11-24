package nz.co.test.transactions.ui.screens.transactiondetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import nz.co.test.transactions.R
import nz.co.test.transactions.domain.entity.Transaction
import nz.co.test.transactions.ui.screens.utils.formatAmountText
import nz.co.test.transactions.ui.screens.utils.formatAmountToDisplayText
import nz.co.test.transactions.ui.screens.utils.formatTransactionDateText
import nz.co.test.transactions.ui.screens.utils.getAmountTextColor
import nz.co.test.transactions.ui.theme.ApplicationTheme
import nz.co.test.transactions.ui.theme.CombinedThemePreviews
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Composable that displays the Transaction details
 */
@Composable
fun TransactionDetailsScreen(
    transaction: Transaction?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {
        transaction?.let { nonNullTransaction ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
                    .testTag(TRANSACTION_DETAILS_SCREEN_SUMMARY),
                style = MaterialTheme.typography.h6,
                text = nonNullTransaction.summary,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .testTag(TRANSACTION_DETAILS_SCREEN_AMOUNT),
                style = MaterialTheme.typography.h3,
                color = getAmountTextColor(nonNullTransaction.amountToDisplay),
                text = formatAmountToDisplayText(
                    amountToDisplay = nonNullTransaction.amountToDisplay,
                    debit = nonNullTransaction.debit,
                    credit = nonNullTransaction.credit,
                ),
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .testTag(TRANSACTION_DETAILS_SCREEN_TRANSACTION_DATE),
                style = MaterialTheme.typography.subtitle2,
                text = formatTransactionDateText(nonNullTransaction.transactionDate),
                textAlign = TextAlign.Start,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .testTag(TRANSACTION_DETAILS_SCREEN_GST),
                style = MaterialTheme.typography.subtitle1,
                text = "${stringResource(R.string.label_gst)} ${formatAmountText(nonNullTransaction.gstAmount)}",
                textAlign = TextAlign.Start,
            )
        }
    }
}

/**
 * A Preview Composable for [TransactionDetailsScreen]
 */
@CombinedThemePreviews
@Composable
private fun TransactionDetailsScreenPreview() {
    val transaction = Transaction(
        id = 1,
        transactionDate = LocalDateTime.of(2021, 8, 31, 15, 47, 10, 0),
        summary = "Test Summary 1",
        debit = BigDecimal.valueOf(9379.55),
        credit = BigDecimal.valueOf(0),
    )
    ApplicationTheme {
        TransactionDetailsScreen(transaction = transaction)
    }
}

const val TRANSACTION_DETAILS_SCREEN_SUMMARY = "transaction_details_screen:text_summary"
const val TRANSACTION_DETAILS_SCREEN_AMOUNT = "transaction_details_screen:text_amount"
const val TRANSACTION_DETAILS_SCREEN_TRANSACTION_DATE =
    "transaction_details_screen:text_transaction_date"
const val TRANSACTION_DETAILS_SCREEN_GST = "transaction_details_screen:text_gst"

