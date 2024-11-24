package nz.co.test.transactions.ui.screens.transactiondetails

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import nz.co.test.transactions.domain.entity.Transaction
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
 * Test class for [TransactionDetailsScreenViewModel]
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransactionDetailsScreenViewModelTest {

    private lateinit var underTest: TransactionDetailsScreenViewModel

    private val savedStateHandle = mock<SavedStateHandle>()

    @BeforeEach
    fun resetMocks() {
        reset(savedStateHandle)
    }

    private fun initializeUnderTest(
        transactionIdStr: String = "1",
        transactionDateStr: String = "2021-08-31T15:47:10",
        transactionSummary: String = "Hackett, Stamm and Kuhn",
        transactionDebitStr: String = "9379.55",
        transactionCreditStr: String = "0",
    ) {
        whenever(savedStateHandle.get<String>(TransactionDetailsScreenViewModel.KEY_ID)).thenReturn(
            transactionIdStr
        )
        whenever(savedStateHandle.get<String>(TransactionDetailsScreenViewModel.KEY_TRANSACTION_DATE)).thenReturn(
            transactionDateStr
        )
        whenever(savedStateHandle.get<String>(TransactionDetailsScreenViewModel.KEY_SUMMARY)).thenReturn(
            transactionSummary
        )
        whenever(savedStateHandle.get<String>(TransactionDetailsScreenViewModel.KEY_DEBIT)).thenReturn(
            transactionDebitStr
        )
        whenever(savedStateHandle.get<String>(TransactionDetailsScreenViewModel.KEY_CREDIT)).thenReturn(
           transactionCreditStr
        )

        underTest = TransactionDetailsScreenViewModel(savedStateHandle)
    }

    @Test
    fun `test that the transaction details are retrieved from compose navigation upon initialization`() =
        runTest {
            initializeUnderTest()

            val transactionResult = Transaction(
                id = 1,
                transactionDate = LocalDateTime.of(2021, 8, 31, 15, 47, 10, 0),
                summary = "Hackett, Stamm and Kuhn",
                debit = BigDecimal("9379.55"),
                credit = BigDecimal("0"),
            )

            underTest.uiState.test {
                assertThat(awaitItem().transaction).isEqualTo(transactionResult)
            }
        }

    @Test
    fun `test that the transaction details are not retrieved when an error occurs`() = runTest {
        // Pass an invalid ID to fail the retrieval
        assertDoesNotThrow { initializeUnderTest(transactionIdStr = "1.5") }

        underTest.uiState.test {
            assertThat(awaitItem().transaction).isNull()
        }
    }
}