package com.bharath.expensetracker.presentation.uielements.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(
    val label: String,
    val icon: ImageVector,
    val route: String,
) {
    object AccountsScreen : Screens(
        label = "Khata",
        icon = Icons.Outlined.AccountBalanceWallet,
        route = "KhataScreen",
    )
    object KhataDetailScreen :Screens(
        label = "KhataDetail",
        icon =  Icons.Outlined.AccountBalanceWallet,
        route = "KhataDetail"

    )

    object KhataAddScreen :Screens(
        label = "KhataAdd",
        icon = Icons.Default.Add,
        route = "KhataAdd"
    )
}
