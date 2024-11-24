package nz.co.test.transactions.domain.usecase

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
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
import java.time.OffsetDateTime
import java.time.ZoneOffset

/**
 * Test class for [GetTransactionsUseCase]
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetTransactionsUseCaseTest {

    private lateinit var underTest: GetTransactionsUseCase

    private val transactionsRepository = mock<TransactionRepository>()

    @BeforeAll
    fun setUp() {
        underTest = GetTransactionsUseCase(transactionsRepository)
    }

    @BeforeEach
    fun resetMocks() {
        reset(transactionsRepository)
    }

    @Test
    fun `test that the list of transactions is retrieved`() = runTest {
        val transactions = listOf(
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
        whenever(transactionsRepository.getTransactions()).thenReturn(transactions)

        assertThat(underTest()).isEqualTo(transactions)
    }
}