package nz.co.test.transactions.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nz.co.test.transactions.domain.entity.Transaction
import nz.co.test.transactions.ui.screens.transactionslist.TransactionsListScreenViewModel
import nz.co.test.transactions.ui.screens.transactionslist.TransactionsListScreen

/**
 * Route to show the [TransactionsListScreen]
 */
@Composable
fun TransactionsListRoute(
    viewModel: TransactionsListScreenViewModel = hiltViewModel(),
    onTransactionClick: (Transaction) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TransactionsListScreen(
        savedScrollPosition = uiState.savedScrollPosition,
        transactions = uiState.transactions,
        onScrollPositionChanged = viewModel::saveScrollPosition,
        onTransactionClick = onTransactionClick,
    )
}