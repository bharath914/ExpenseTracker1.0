package com.bharath.expensetracker.screens.graphscreen.util

sealed class GraphPagelist(
    val label:String,
    val type:String
){
object RangeOfIncome: GraphPagelist(
    label = "Range Of Income",
    type = "Income"

)
object RangeOfExpense: GraphPagelist(
    label = "Range Of Expense",
    type = "Expense"

)
}

