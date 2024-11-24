package nz.co.test.transactions.ui

import nz.co.test.transactions.domain.entity.Transaction

/**
 * Data class representing the UI state of the Transactions screen
 */
data class TransactionsUiState(
    val transactions: List<Transaction> = emptyList(),
)