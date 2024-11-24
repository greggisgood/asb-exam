package nz.co.test.transactions.data.di.dispatcher

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import nz.co.test.transactions.domain.qualifier.IoDispatcher

/**
 * Module to provide Coroutine Dispatchers
 */
@Module
@InstallIn(SingletonComponent::class)
object CoroutineDispatchersModule {

    /**
     * Provides the IO Dispatcher
     */
    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}