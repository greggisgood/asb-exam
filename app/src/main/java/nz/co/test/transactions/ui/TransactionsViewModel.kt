package nz.co.test.transactions.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nz.co.test.transactions.domain.usecase.GetTransactionsUseCase
import timber.log.Timber
import javax.inject.Inject

/**
 * The ViewModel for transactions related operations
 */
@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionsUiState())

    val uiState = _uiState.asStateFlow()

    init {
        getTransactions()
    }

    /**
     * Retrieves the list of transactions made by the user
     */
    private fun getTransactions() {
        viewModelScope.launch {
            runCatching {
                getTransactionsUseCase()
            }.onSuccess { transactions ->
                _uiState.update { it.copy(transactions = transactions) }
            }.onFailure { exception ->
                Timber.e(exception, "There was an error retrieving the list of transactions")
            }
        }
    }
}