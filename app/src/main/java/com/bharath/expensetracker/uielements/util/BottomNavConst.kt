package com.bharath.expensetracker.uielements.util

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.res.painterResource
import com.bharath.expensetracker.R
import com.bharath.expensetracker.uielements.BottomNavItem

object BottomNavConst {
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
        )
    )


}