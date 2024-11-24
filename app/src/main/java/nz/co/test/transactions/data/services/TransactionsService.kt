package nz.co.test.transactions.data.services

import nz.co.test.transactions.domain.entity.Transaction
import retrofit2.http.GET

/**
 * Retrofit Service class containing Transactions related calls
 */
interface TransactionsService {

    /**
     * Retrieves the list of transactions made by the user
     *
     * @return The list of transactions
     */
    @GET("Josh-Ng/500f2716604dc1e8e2a3c6d31ad01830/raw/4d73acaa7caa1167676445c922835554c5572e82/test-data.json")
    suspend fun getTransactions(): List<Transaction>
}

