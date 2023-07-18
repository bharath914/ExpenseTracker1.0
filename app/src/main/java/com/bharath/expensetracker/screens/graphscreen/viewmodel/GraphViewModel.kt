package com.bharath.expensetracker.screens.graphscreen.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.expensetracker.common.Cons
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.data.repository.RepositoryInterface
import com.bharath.expensetracker.screens.graphscreen.ui.screens.data.Details
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
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

    private val _listOfMonths = mutableStateOf(emptyList<String>())
    val listOfMonths :State<List<String>> = _listOfMonths

 private val _listOfMonthsSUm = mutableStateOf(emptyList<Float>())
    val listOfMonthsSum :State<List<Float>> = _listOfMonthsSUm


    val listCategoriesExp: MutableList<Details> = mutableListOf()
    val listCategoriesInc: MutableList<Details> = mutableListOf()




















    init {

         viewModelScope.launch(Dispatchers.IO) {
            expenseSum = repository.getSumOfTransaction("Expense",currentMonth)

        }
        viewModelScope.launch(Dispatchers.IO) {
            incomeSum = repository.getSumOfTransaction("Income",currentMonth)

        }


        viewModelScope.launch(Dispatchers.IO) {
            highestTransactionExp = repository.getHighestPayment("Expense",currentMonth)
        }.start()

        viewModelScope.launch(Dispatchers.IO) {
            lowestTransaction = repository.getHighestPayment("Income",currentMonth)
        }.start()
        isLoadedRangeHigh.value = true
        isLoadedRangeLow.value = true
        viewModelScope.launch(Dispatchers.IO){
            Cons.expenseListCategories.forEachIndexed { i, s ->
                val sum =repository.getCategorySum(s,currentMonth,"Expense")
                if (sum>=1){
                listCategoriesExp.add(Details(Value=sum,name=s, color = Cons.colorList[i]))
                    listCategoriesExp.sortByDescending { details ->
                    details.Value

                    }
            }
            }

            }


        viewModelScope.launch(Dispatchers.IO){
            Cons.incomeListCategories.forEachIndexed { i, s ->
                val sum=repository.getCategorySum(s,currentMonth,"Income")
                if (sum >=1) {
                    listCategoriesInc.add(Details(Value =sum, name = s,color=Cons.colorList[i]))
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

    fun getMontlySumAndMonths(type: String){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMonthsActive().collectLatest {list->
                _listOfMonths.value = list
            }

        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMonthlySum(type).collectLatest {
                _listOfMonthsSUm.value = it
            }
        }

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