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
                        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

                        TopAppBar(
                            title = {
                                when (currentRoute) {
                                    Screen.TransactionsList.route -> {
                                        Text(stringResource(R.string.route_title_transactions))
                                    }
                                    Screen.TransactionDetails.route -> {
                                        Text(stringResource(R.string.route_title_transaction_details))
                                    }
                                }
                            },
                            navigationIcon = {
                                if (currentRoute == Screen.TransactionDetails.route) {
                                    IconButton(onClick = { navController.popBackStack() }) {
                                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back Button")
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
                                onTransactionClick = {
                                    // Navigate to the Transaction Details Route
                                }
                            )
                        }
                        composable(Screen.TransactionDetails.route) {
                            // Create the Transaction Details Route and show the page
                        }
                    }
                }
            }
        }
    }
}