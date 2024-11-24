package nz.co.test.transactions.domain.usecase

import nz.co.test.transactions.domain.repository.TransactionRepository
import javax.inject.Inject

/**
 * Use Case that retrieves the list of Transactions made by the user. The Transactions are sorted
 * from latest to oldest Transaction date
 */
class GetTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {

    /**
     * Invocation function
     *
     * @return The list of sorted Transactions
     */
    suspend operator fun invoke() = transactionRepository.getTransactions()
}