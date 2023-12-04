package com.bharath.expensetracker.presentation.screens.graphscreen.util

import androidx.compose.ui.graphics.Brush
import com.bharath.expensetracker.ui.theme.Money1inc
import com.bharath.expensetracker.ui.theme.Money2inc

sealed class GraphPagelist(
    val label: String,
    val type: String,
    val brush: Brush,
    val Hightitle: String,
    val Lowtitle: String,
    val highkey: String,
    val lowkey: String,
) {
    object RangeOfIncome : GraphPagelist(
        label = "Range Of Income",
        type = "Income",
        brush = Brush.linearGradient(
            listOf(
                Money1inc,
                Money2inc
            )
        ),
        Hightitle = "Highest Income",
        Lowtitle = "Lowest Income",
        highkey = "highIncome",
        lowkey = "lowIncome"


    )

    object RangeOfExpense : GraphPagelist(
        label = "Range Of Expense",
        type = "Expense",
        brush = Brush.linearGradient(
            listOf(
                Money1inc,
                Money2inc
            )
        ),
        Hightitle = "Highest Expense",
        Lowtitle = "Lowest Expense",
        highkey = "highExpense",
        lowkey = "lowExpense"

    )
}

