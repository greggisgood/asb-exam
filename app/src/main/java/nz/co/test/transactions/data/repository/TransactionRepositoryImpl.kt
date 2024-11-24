package nz.co.test.transactions.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import nz.co.test.transactions.data.services.TransactionsService
import nz.co.test.transactions.domain.qualifier.IoDispatcher
import nz.co.test.transactions.domain.repository.TransactionRepository
import javax.inject.Inject

/**
 * The default implementation of [TransactionRepository]
 */
class TransactionRepositoryImpl @Inject constructor(
    private val transactionsService: TransactionsService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : TransactionRepository {

    override suspend fun getTransactions() = withContext(ioDispatcher) {
        transactionsService.getTransactions()
    }
}