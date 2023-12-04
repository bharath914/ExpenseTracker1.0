package com.bharath.expensetracker.presentation.uielements.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bharath.expensetracker.MainViewModel
import com.bharath.expensetracker.presentation.screens.addscreen.ui.NewAddScreen
import com.bharath.expensetracker.presentation.screens.allTransactionsScreen.ui.AllTransactionsScreen
import com.bharath.expensetracker.presentation.screens.graphscreen.ui.GraphScreen
import com.bharath.expensetracker.presentation.screens.homescreen.ui.HomeScreen
import com.bharath.expensetracker.presentation.screens.khata.KhataAddScreen
import com.bharath.expensetracker.presentation.screens.khata.KhataDetailScreen
import com.bharath.expensetracker.presentation.screens.khata.KhataScreen
import com.bharath.expensetracker.presentation.screens.settings.ui.SettingsPage


@Composable
fun NavHostContainer(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    mainViewModel: MainViewModel
) {
    NavHost(navController = navHostController,
        startDestination = "home",
        builder = {
            composable("home") {
                HomeScreen(onclick = { navHostController.navigate("add") })

            }
            composable("graph") {
                GraphScreen()

            }
            composable("add") {
                NewAddScreen()


            }
            composable("profile") {
                AllTransactionsScreen()


            }
            composable("Settings") {
                SettingsPage()

            }


            composable(Screens.AccountsScreen.route) {
                KhataScreen(
                    navHostController,
                    mainViewModel
                )
            }

            composable(Screens.KhataDetailScreen.route) {
                KhataDetailScreen(navHostController = navHostController,mainViewModel)
            }

            composable(Screens.KhataAddScreen.route) {
                KhataAddScreen(navHostController = navHostController,mainViewModel)
            }
        })
}