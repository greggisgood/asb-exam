package nz.co.test.transactions.ui.screens.transactionslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nz.co.test.transactions.domain.usecase.GetTransactionsByDateUseCase
import timber.log.Timber
import javax.inject.Inject

/**
 * The ViewModel for the screen displaying the list of Transactions
 */
@HiltViewModel
class TransactionsListScreenViewModel @Inject constructor(
    private val getTransactionsByDateUseCase: GetTransactionsByDateUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionsListScreenUiState())

    val uiState = _uiState.asStateFlow()

    init {
        getTransactionsByDate()
    }

    /**
     * Retrieves the list of Transactions made by the user. The Transactions are sorted from latest
     * to oldest Transaction date
     */
    private fun getTransactionsByDate() {
        viewModelScope.launch {
            runCatching {
                getTransactionsByDateUseCase()
            }.onSuccess { transactions ->
                _uiState.update { it.copy(transactions = transactions) }
            }.onFailure { exception ->
                Timber.e(exception, "There was an error retrieving the list of transactions")
            }
        }
    }

    /**
     * Save the scroll position of the Transactions list, so that it can be restored upon screen
     * recomposition or navigating back
     *
     * @param newScrollPosition the new scroll position
     */
    fun saveScrollPosition(newScrollPosition: Int) {
        val currentScrollPosition = _uiState.value.savedScrollPosition
        if (currentScrollPosition != newScrollPosition) {
            _uiState.update { it.copy(savedScrollPosition = newScrollPosition) }
        }
    }
}