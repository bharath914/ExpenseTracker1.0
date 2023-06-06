package com.bharath.expensetracker.screens.homescreen.util

sealed class HomePageLists(
    val title:String,
    val keyWord: String

){

    object RecentExpenses : HomePageLists(
        title = "Recent Expenses",
        keyWord = "Expense"
    )
    object RecentIncome:HomePageLists(
        title = "Recent Income",
        keyWord = "Income"
    )


}
