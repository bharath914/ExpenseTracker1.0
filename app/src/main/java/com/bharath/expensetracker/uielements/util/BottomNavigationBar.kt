package com.bharath.expensetracker.uielements.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomNavigationBarCus(
    navHostController: NavHostController,
) {
    BottomNavigation(

        backgroundColor = MaterialTheme.colorScheme.surface,
    ) {

        val backStackEntry by navHostController.currentBackStackEntryAsState()


        val currentRoute = backStackEntry?.destination?.route
        BottomNavConst.BottomNavItems.forEach {
            BottomNavigationItem(
                selected = currentRoute == it.route,
                onClick = {
                    navHostController.navigate(it.route)


                },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.label,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                },
                label = {
                    Text(
                        text = it.label,
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1,
                        modifier = Modifier.basicMarquee()
                    )


                },
                alwaysShowLabel = false,

                )


        }

    }
}