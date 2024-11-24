package nz.co.test.transactions.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import nz.co.test.transactions.R
import nz.co.test.transactions.ui.navigation.Screen
import nz.co.test.transactions.ui.navigation.TransactionDetailsRoute
import nz.co.test.transactions.ui.navigation.TransactionsListRoute
import nz.co.test.transactions.ui.theme.ApplicationTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            ApplicationTheme {
                Scaffold(
                    topBar = {
                        val currentRoute =
                            navController.currentBackStackEntryAsState().value?.destination?.route

                        TopAppBar(
                            title = {
                                when {
                                    currentRoute == Screen.TransactionsList.route -> {
                                        Text(stringResource(R.string.route_title_transactions))
                                    }
                                    currentRoute?.contains(Screen.TransactionDetails.route) == true -> {
                                        Text(stringResource(R.string.route_title_transaction_details))
                                    }
                                }
                            },
                            navigationIcon = {
                                if (currentRoute?.contains(Screen.TransactionDetails.route) == true) {
                                    IconButton(onClick = { navController.popBackStack() }) {
                                        Icon(
                                            Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Back Button"
                                        )
                                    }
                                }
                            }
                        )
                    },
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Screen.TransactionsList.route,
                    ) {
                        composable(Screen.TransactionsList.route) {
                            TransactionsListRoute(
                                onTransactionClick = { transaction ->
                                    val route =
                                        Screen.TransactionDetails.route +
                                                "/${transaction.id}/" +
                                                "${transaction.transactionDate}/" +
                                                "${transaction.summary}/" +
                                                "${transaction.debit}/" +
                                                "${transaction.credit}"
                                    navController.navigate(route)
                                }
                            )
                        }
                        composable("${Screen.TransactionDetails.route}/{id}/{transactionDate}/{summary}/{debit}/{credit}") {
                            // The Navigation parameters are automatically by
                            // TransactionDetailsScreenViewModel through the SavedStateHandle
                            TransactionDetailsRoute()
                        }
                    }
                }
            }
        }
    }
}