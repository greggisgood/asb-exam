package nz.co.test.transactions.ui

import androidx.compose.runtime.Immutable
import nz.co.test.transactions.domain.entity.Transaction

/**
 * Data class representing the UI state of the Transactions screen
 */
@Immutable
data class TransactionsUiState(
    val transactions: List<Transaction> = emptyList(),
    val savedScrollPosition: Int = -1,
)