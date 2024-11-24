package nz.co.test.transactions.ui

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import nz.co.test.transactions.domain.entity.Transaction
import nz.co.test.transactions.domain.usecase.GetTransactionsByDateUseCase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.reset
import org.mockito.kotlin.whenever
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * Test class for [TransactionsListScreenViewModel]
 */
@OptIn(ExperimentalCoroutinesApi::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransactionsListScreenViewModelTest {

    private lateinit var underTest: TransactionsListScreenViewModel

    private val getTransactionsByDateUseCase = mock<GetTransactionsByDateUseCase>()

    private val transactions = listOf(
        Transaction(
            id = 1,
            transactionDate = LocalDateTime.of(2022, 2, 17, 10, 44, 35, 0),
            summary = "Test Summary 2",
            debit = BigDecimal.valueOf(3461.35),
            credit = BigDecimal.valueOf(0),
        ),
        Transaction(
            id = 1,
            transactionDate = LocalDateTime.of(2021, 8, 31, 15, 47, 10, 0),
            summary = "Test Summary 1",
            debit = BigDecimal.valueOf(9379.55),
            credit = BigDecimal.valueOf(0),
        ),
    )

    @BeforeEach
    fun setDispatcherAndResetMocks() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        reset(getTransactionsByDateUseCase)
    }

    @AfterEach
    fun resetDispatcher() {
        Dispatchers.resetMain()
    }

    private fun initializeUnderTest() {
        underTest = TransactionsListScreenViewModel(getTransactionsByDateUseCase)
    }

    @Test
    fun `test that the list of sorted transactions is retrieved and saved in the UI state`() =
        runTest {
            whenever(getTransactionsByDateUseCase()).thenReturn(transactions)

            initializeUnderTest()

            underTest.uiState.test {
                assertThat(awaitItem().transactions).isEqualTo(transactions)
            }
        }

    @Test
    fun `test that the UI state is not updated when there is an error retrieving the list of sorted transactions`() =
        runTest {
            whenever(getTransactionsByDateUseCase()).thenThrow(RuntimeException())

            assertDoesNotThrow { initializeUnderTest() }

            underTest.uiState.test {
                assertThat(awaitItem().transactions).isEmpty()
            }
        }

    @Test
    fun `test that the new transaction list scroll state is saved`() = runTest {
        val scrollState = 100
        whenever(getTransactionsByDateUseCase()).thenReturn(transactions)
        initializeUnderTest()

        underTest.saveScrollPosition(scrollState)

        underTest.uiState.test {
            assertThat(awaitItem().savedScrollPosition).isEqualTo(scrollState)
        }
    }
}