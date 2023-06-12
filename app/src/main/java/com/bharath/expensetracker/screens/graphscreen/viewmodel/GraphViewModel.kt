package com.bharath.expensetracker.screens.graphscreen.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.data.repository.RepositoryInterface
import com.bharath.expensetracker.screens.graphscreen.ui.screens.data.Details
import com.bharath.expensetracker.ui.theme.colorBar1
import com.bharath.expensetracker.ui.theme.colorBar10
import com.bharath.expensetracker.ui.theme.colorBar11
import com.bharath.expensetracker.ui.theme.colorBar12
import com.bharath.expensetracker.ui.theme.colorBar2
import com.bharath.expensetracker.ui.theme.colorBar3
import com.bharath.expensetracker.ui.theme.colorBar4
import com.bharath.expensetracker.ui.theme.colorBar5
import com.bharath.expensetracker.ui.theme.colorBar6
import com.bharath.expensetracker.ui.theme.colorBar7
import com.bharath.expensetracker.ui.theme.colorBar8
import com.bharath.expensetracker.ui.theme.colorBar9
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class GraphViewModel @Inject constructor(
    private val repository: RepositoryInterface,
) : ViewModel() {

    var expenseSum: Flow<Float> = emptyFlow()
    var incomeSum: Flow<Float> = emptyFlow()
    var isLoadingG = mutableStateOf(false)
    var isLoadedRangeHigh = mutableStateOf(false)
    var isLoadedRangeLow = mutableStateOf(false)
    var highestTransactionExp: List<Transactions> = emptyList()
    var currentMonth ="${LocalDate.now().month}"
    var lowestTransaction: List<Transactions> = emptyList()



    val listCategoriesExp: MutableList<Details> = mutableListOf()
    val listCategoriesInc: MutableList<Details> = mutableListOf()


    val incomeListCategories = listOf(
        "Salary",
        "Business Profits",
        "Shares & Stocks",
        "CashBack's",
        "Gifts",
        "Passive Income"
    )
    val expenseListCategories = listOf(
        "Clothing & Apparel",
        "Food",
        "Rent & Monthly Expenses",
        "Movie's & Other",
        "Vehicle & Accessories",
        "Travel",
        "Utilities & Essentials",
        "Electronics",
        "Recharges &Bill Payments",
        "Furniture & Home Equipment",
        "Other"
    )

    val colorList = listOf(
        colorBar1,
        colorBar2,
        colorBar3,
        colorBar4,
        colorBar5,
        colorBar6,
        colorBar7,
        colorBar8,
        colorBar9,
        colorBar10,
        colorBar11,
        colorBar12,



        )





















    init {

         viewModelScope.launch(Dispatchers.IO) {
            expenseSum = repository.getSumOfTransaction("Expense",currentMonth)
            incomeSum = repository.getSumOfTransaction("Income",currentMonth)

        }.start()


        viewModelScope.launch(Dispatchers.IO) {
            highestTransactionExp = repository.getHighestPayment("Expense")
        }.start()

        viewModelScope.launch(Dispatchers.IO) {
            lowestTransaction = repository.getHighestPayment("Income")
        }.start()
        isLoadedRangeHigh.value = true
        isLoadedRangeLow.value = true
        viewModelScope.launch(Dispatchers.IO){
            expenseListCategories.forEachIndexed { i, s ->
                val sum =repository.getCategorySum(s,currentMonth,"Expense")
                if (sum>=1){
                listCategoriesExp.add(Details(Value=sum,name=s, color = colorList[i]))
                    listCategoriesExp.sortByDescending { details ->
                    details.Value

                    }
            }
            }

            }


        viewModelScope.launch(Dispatchers.IO){
            incomeListCategories.forEachIndexed { i, s ->
                val sum=repository.getCategorySum(s,currentMonth,"Income")
                if (sum >=1) {
                    listCategoriesInc.add(Details(Value =sum, name = s,color=colorList[i]))
                    listCategoriesInc.sortByDescending {
                        details ->
                        details.Value
                    }
                }



            }

        }


        isLoadingG.value = true

    }


    fun getHighestTransaction(type: String): List<Transactions> {


        return highestTransactionExp
    }

    fun getLowestTransaction(type: String): List<Transactions> {


        return lowestTransaction
    }
    fun getSumOfCategories(category:String,month:String,type: String):Float{
        var sum= 0f
        viewModelScope.launch(Dispatchers.IO){

        sum = repository.getCategorySum(category,month, type = type)
         }
        return sum


    }


    fun getNumber(string: String): String {
        var value = string.substring(0, string.indexOf('.'))

        var separator = 3
        var visit = 0
        var number = ""

        for (i in value.length - 1 downTo 0) {

            visit += 1
            number += value[i]
            if (visit == separator && i != 0) {
                number += ","
                separator = 2
                visit = 0
            }
        }

        return number.reversed() + string.substring(string.indexOf('.'), string.length)
    }


}