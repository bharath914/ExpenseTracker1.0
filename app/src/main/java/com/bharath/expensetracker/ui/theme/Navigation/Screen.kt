package com.bharath.expensetracker.ui.theme.Navigation

sealed class Screen(val route:String){
    object Welcome: Screen(route = "welcome_screen")
    object Home: Screen(route = "home_screen")
}
