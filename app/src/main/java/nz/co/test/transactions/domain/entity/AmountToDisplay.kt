package nz.co.test.transactions.domain.entity

/**
 * Enum class that determines what amount type will be displayed in the UI
 */
enum class AmountToDisplay {

    /**
     * The debited amount is shown
     */
    Debit,

    /**
     * The credited amount is shown
     */
    Credit,

    /**
     * A zero amount is shown
     */
    Zero,
}