package nz.co.test.transactions.domain.repository

import nz.co.test.transactions.domain.entity.Transaction

/**
 * Repository containing all Transaction-related operations
 */
interface TransactionRepository {

    /**
     * Retrieves the list of Transactions made by the user
     *
     * @return a list of Transactions
     */
    suspend fun getTransactions(): List<Transaction>
}