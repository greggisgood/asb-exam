package nz.co.test.transactions.ui.navigation

/**
 * Sealed class containing all routes for Compose Navigation
 */
sealed class Screen(val route: String) {

    /**
     * Represents the Transactions List screen
     */
    data object TransactionsList : Screen("transactions_list")

    /**
     * Represents the Transaction Details screen
     */
    data object TransactionDetails : Screen("transaction_details")
}