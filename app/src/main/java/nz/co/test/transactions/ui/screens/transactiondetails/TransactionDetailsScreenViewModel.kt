package nz.co.test.transactions.ui.screens.transactiondetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nz.co.test.transactions.domain.entity.Transaction
import timber.log.Timber
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.inject.Inject

/**
 * The ViewModel for the screen displaying the Transaction details
 */
@HiltViewModel
class TransactionDetailsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val _uiState = MutableStateFlow(TransactionDetailsScreenUiState())

    val uiState = _uiState.asStateFlow()

    init {
        // Retrieve parameters passed from the navigation route
        val transactionIdStr = savedStateHandle.get<String>(KEY_ID) ?: "-1"
        val transactionDateStr = savedStateHandle.get<String>(KEY_TRANSACTION_DATE) ?: ""
        val transactionSummary = savedStateHandle.get<String>(KEY_SUMMARY) ?: ""
        val transactionDebitStr = savedStateHandle.get<String>(KEY_DEBIT) ?: ""
        val transactionCreditStr = savedStateHandle.get<String>(KEY_CREDIT) ?: ""

        // Convert SavedStateHandle parameters into a Transaction object, and save it in the UI state
        runCatching {
            Transaction(
                id = transactionIdStr.toInt(),
                transactionDate = LocalDateTime.parse(transactionDateStr),
                summary = transactionSummary,
                debit = BigDecimal(transactionDebitStr),
                credit = BigDecimal(transactionCreditStr),
            )
        }.onSuccess { transaction ->
            _uiState.update { it.copy(transaction = transaction) }
        }.onFailure { exception ->
            Timber.e(exception, "There was an error retrieving the transaction details")
        }
    }

    companion object {
        const val KEY_ID = "id"
        const val KEY_TRANSACTION_DATE = "transactionDate"
        const val KEY_SUMMARY = "summary"
        const val KEY_DEBIT = "debit"
        const val KEY_CREDIT = "credit"
    }
}