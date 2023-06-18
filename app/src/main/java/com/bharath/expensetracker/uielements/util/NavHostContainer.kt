package com.bharath.expensetracker.uielements.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bharath.expensetracker.screens.addscreen.ui.NewAddScreen
import com.bharath.expensetracker.screens.allTransactionsScreen.ui.AllTransactionsScreen
import com.bharath.expensetracker.screens.graphscreen.ui.GraphScreen
import com.bharath.expensetracker.screens.homescreen.ui.HomeScreen
import com.bharath.expensetracker.screens.settings.ui.SettingsPage


@Composable
fun NavHostContainer(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(navController = navHostController,
        startDestination = "home",
        builder = {
            composable("home") {
                HomeScreen(onclick = {navHostController.navigate("add")})

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
            composable("Settings"){
                SettingsPage()

            }


        })
}