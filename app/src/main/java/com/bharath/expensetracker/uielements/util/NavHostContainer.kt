package com.bharath.expensetracker.uielements.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bharath.expensetracker.screens.addscreen.AddScreen
import com.bharath.expensetracker.screens.graphscreen.ui.GraphScreen
import com.bharath.expensetracker.screens.homescreen.ui.HomeScreen
import com.bharath.expensetracker.screens.allTransactionsScreen.ui.AllTransactionsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavHostContainer(
navHostController: NavHostController,
paddingValues: PaddingValues
) {
    NavHost(navController =navHostController
        ,
        startDestination = "home",
        builder = {
        composable("home") {
            HomeScreen()
        }
            composable("graph"){
                GraphScreen()
            }
            composable("add"){
                AddScreen()
            }
            composable("profile"){
                AllTransactionsScreen()
            }




        })
}