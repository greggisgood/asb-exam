package nz.co.test.transactions.ui.screens.transactiondetails

import androidx.compose.runtime.Immutable
import nz.co.test.transactions.domain.entity.Transaction

/**
 * Data class representing the UI state of the Transaction details screen
 */
@Immutable
data class TransactionDetailsScreenUiState(
    val transaction: Transaction? = null,
)
