package nz.co.test.transactions.domain.repository

import nz.co.test.transactions.domain.entity.Transaction

/**
 * Repository containing all Transaction-related operations
 */
interface TransactionRepository {

    /**
     * Retrieves the list of Transactions made by the user. The list of Transactions are sorted
     * from latest to oldest transaction date
     *
     * @return The list of sorted Transactions
     */
    suspend fun getTransactionsByDate(): List<Transaction>
}