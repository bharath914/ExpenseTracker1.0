package com.bharath.expensetracker.ui.theme.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bharath.expensetracker.homescreen.HomeScreen
import com.bharath.expensetracker.ui.theme.Screen.WelcomeScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination:String
) {
    NavHost(navController = navController,
        startDestination = startDestination){
        composable(route= Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }
        composable(route= Screen.Home.route){
HomeScreen()
        }
    }
}