package com.bharath.expensetracker.uielements.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.Settings

object  BottomNavConst {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = "home"
        ),
        BottomNavItem(
            label = "Insights",
            icon = Icons.Filled.Insights,
            route = "graph"
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
        BottomNavItem(
                label = "Settings",
            icon = Icons.Default.Settings,
            route = "Settings"
        )
    )


}