package nz.co.test.transactions.data.repository

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import nz.co.test.transactions.data.services.TransactionsService
import nz.co.test.transactions.domain.entity.Transaction
import nz.co.test.transactions.domain.repository.TransactionRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.kotlin.mock
import org.mockito.kotlin.reset
import org.mockito.kotlin.whenever
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Test class for [TransactionRepositoryImpl]
 */
@OptIn(ExperimentalCoroutinesApi::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransactionRepositoryImplTest {

    private lateinit var underTest: TransactionRepository

    private val transactionsService = mock<TransactionsService>()

    @BeforeAll
    fun setUp() {
        underTest = TransactionRepositoryImpl(
            transactionsService = transactionsService,
            ioDispatcher = UnconfinedTestDispatcher()
        )
    }

    @BeforeEach
    fun resetMocks() {
        reset(transactionsService)
    }

    @Test
    fun `test that the list of transactions are retrieved and sorted from latest to earliest transaction date`() =
        runTest {
            // The oldest Transaction
            val transactionOne = Transaction(
                id = 1,
                transactionDate = LocalDateTime.of(2021, 8, 31, 15, 47, 10, 0),
                summary = "Test Summary 1",
                debit = BigDecimal.valueOf(9379.55),
                credit = BigDecimal.valueOf(0),
            )
            // The latest Transaction
            val transactionTwo = Transaction(
                id = 1,
                transactionDate = LocalDateTime.of(2022, 2, 17, 10, 44, 35, 0),
                summary = "Test Summary 2",
                debit = BigDecimal.valueOf(3461.35),
                credit = BigDecimal.valueOf(0),
            )

            // The Service simulates the return of unordered Transactions
            whenever(transactionsService.getTransactions()).thenReturn(listOf(transactionOne, transactionTwo))

            // The Repository returns the Transactions that are sorted
            assertThat(underTest.getTransactionsByDate()).isEqualTo(listOf(transactionTwo, transactionOne))
        }
}