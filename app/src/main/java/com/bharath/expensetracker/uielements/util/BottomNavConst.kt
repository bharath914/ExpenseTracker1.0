package com.bharath.expensetracker.uielements.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import com.bharath.expensetracker.uielements.BottomNavItem

object BottomNavConst {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = "home"
        ),
        BottomNavItem(
            label = "Graph",
            icon = Icons.Filled.Favorite,
            route = "graph"
        ),
        BottomNavItem(
          label = "Add",
          icon = Icons.Default.Add,
          route = "add"
        ),
        BottomNavItem(
            label = "Profile",
            icon = Icons.Filled.Person,
            route = "profile"
        )
    )


}