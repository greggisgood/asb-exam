package nz.co.test.transactions.data.di.transaction

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nz.co.test.transactions.data.repository.TransactionRepositoryImpl
import nz.co.test.transactions.domain.repository.TransactionRepository

/**
 * Module that provides dependencies for transactions related operations
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class TransactionModule {

    /**
     * Binds the implementation to [TransactionRepository]
     */
    @Binds
    abstract fun bindTransactionRepository(implementation: TransactionRepositoryImpl): TransactionRepository
}