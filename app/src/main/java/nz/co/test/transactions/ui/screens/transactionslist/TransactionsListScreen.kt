package nz.co.test.transactions.ui.screens.transactionslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import nz.co.test.transactions.domain.entity.Transaction
import nz.co.test.transactions.ui.theme.ApplicationTheme
import nz.co.test.transactions.ui.theme.CombinedThemePreviews
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Composable that displays the list of transactions made by the user
 */
@Composable
fun TransactionsListScreen(
    savedScrollPosition: Int,
    transactions: List<Transaction>,
    onScrollPositionChanged: (Int) -> Unit,
    onTransactionClick: (Transaction) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    val currentFirstVisibleItem = remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex }
    }

    // Move to the new scroll position when it changes
    LaunchedEffect(savedScrollPosition) {
        if (savedScrollPosition >= 0) {
            lazyListState.animateScrollToItem(savedScrollPosition)
        }
    }
    // Save the new scroll position to the ViewModel after a short delay
    LaunchedEffect(currentFirstVisibleItem.value) {
        delay(500)
        onScrollPositionChanged(currentFirstVisibleItem.value)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = lazyListState,
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(transactions) { transaction ->
            TransactionsListItem(
                modifier = Modifier.testTag("${TRANSACTIONS_LIST_SCREEN_ENTRY}_${transaction.id}"),
                transaction = transaction,
                onClick = { onTransactionClick(transaction) },
            )
        }
    }
}

/**
 * A Preview Composable for [TransactionsListScreen]
 */
@CombinedThemePreviews
@Composable
private fun TransactionsListScreenPreview() {
    val transaction = Transaction(
        id = 1,
        transactionDate = LocalDateTime.of(2021, 8, 31, 15, 47, 10, 0),
        summary = "Test Summary 1",
        debit = BigDecimal.valueOf(9379.55),
        credit = BigDecimal.valueOf(0),
    )

    ApplicationTheme {
        TransactionsListScreen(
            savedScrollPosition = 0,
            transactions = listOf(transaction, transaction, transaction),
            onScrollPositionChanged = {},
            onTransactionClick = {},
        )
    }
}

const val TRANSACTIONS_LIST_SCREEN_ENTRY = "transactions_list_screen:transactions_list_item"

