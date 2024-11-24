package nz.co.test.transactions.domain.usecase

import nz.co.test.transactions.domain.repository.TransactionRepository
import javax.inject.Inject

/**
 * Use Case that retrieves the list of transactions made by the user
 */
class GetTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {

    /**
     * Invocation function
     *
     * @return The list of transactions
     */
    suspend operator fun invoke() = transactionRepository.getTransactions()
}