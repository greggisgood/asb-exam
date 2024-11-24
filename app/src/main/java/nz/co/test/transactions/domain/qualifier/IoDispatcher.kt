package nz.co.test.transactions.domain.qualifier

import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

/**
 * Represents the [Dispatchers.IO]
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher
