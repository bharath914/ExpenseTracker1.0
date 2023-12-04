package com.bharath.expensetracker.presentation.uielements.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountBalanceWallet

object BottomNavConst {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = "home"
        ),
        BottomNavItem(
            label = "Khata",
            icon = Icons.Outlined.AccountBalanceWallet,
            route = "KhataScreen"
        ),
        BottomNavItem(
            label = "Add",
            icon = Icons.Default.Add,
            route = "add"
        ),
        BottomNavItem(
            label = "History",
            icon = Icons.Default.History,
            route = "profile"
        ),
//        BottomNavItem(
//            label = "Settings",
//            icon = Icons.Default.Settings,
//            route = "Settings"
//        ),
    )

}