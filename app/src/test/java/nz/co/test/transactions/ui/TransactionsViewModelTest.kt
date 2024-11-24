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
import nz.co.test.transactions.domain.usecase.GetTransactionsUseCase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.reset
import org.mockito.kotlin.whenever
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.time.ZoneOffset

/**
 * Test class for [TransactionsViewModel]
 */
@OptIn(ExperimentalCoroutinesApi::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransactionsViewModelTest {

    private lateinit var underTest: TransactionsViewModel

    private val getTransactionsUseCase = mock<GetTransactionsUseCase>()

    private val transactions = listOf(
        Transaction(
            id = 1,
            transactionDate = OffsetDateTime.of(
                2021,
                8,
                31,
                15,
                47,
                10,
                0,
                ZoneOffset.ofHours(2)
            ),
            summary = "Test Summary 1",
            debit = BigDecimal.valueOf(9379.55),
            credit = BigDecimal.valueOf(0),
        ),
        Transaction(
            id = 1,
            transactionDate = OffsetDateTime.of(
                2022,
                2,
                17,
                10,
                44,
                35,
                0,
                ZoneOffset.ofHours(2)
            ),
            summary = "Test Summary 2",
            debit = BigDecimal.valueOf(3461.35),
            credit = BigDecimal.valueOf(0),
        ),
    )

    @BeforeEach
    fun setDispatcherAndResetMocks() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        reset(getTransactionsUseCase)
    }

    @AfterEach
    fun resetDispatcher() {
        Dispatchers.resetMain()
    }

    private fun initializeUnderTest() {
        underTest = TransactionsViewModel(getTransactionsUseCase)
    }

    @Test
    fun `test that the list of transactions is retrieved and saved in the UI state`() = runTest {
        whenever(getTransactionsUseCase()).thenReturn(transactions)

        initializeUnderTest()

        underTest.uiState.test {
            assertThat(awaitItem().transactions).isEqualTo(transactions)
        }
    }

    @Test
    fun `test that the UI state is not updated when there is an error retrieving the list of transactions`() =
        runTest {
            whenever(getTransactionsUseCase()).thenThrow(RuntimeException())

            assertDoesNotThrow { initializeUnderTest() }

            underTest.uiState.test {
                assertThat(awaitItem().transactions).isEmpty()
            }
        }
}