package nz.co.test.transactions.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nz.co.test.transactions.ui.screens.transactiondetails.TransactionDetailsScreen
import nz.co.test.transactions.ui.screens.transactiondetails.TransactionDetailsScreenViewModel

/**
 * Route to show the [TransactionDetailsScreen]
 */
@Composable
fun TransactionDetailsRoute(
    viewModel: TransactionDetailsScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TransactionDetailsScreen(
        transaction = uiState.transaction,
    )
}