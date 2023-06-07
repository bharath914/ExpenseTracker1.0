package com.bharath.expensetracker.screens.graphscreen.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.data.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
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

    var LowestTransaction: List<Transactions> = emptyList()


    init {

        val j = viewModelScope.launch(Dispatchers.IO) {
            expenseSum = repository.getSumOfTransaction("Expense")
            incomeSum = repository.getSumOfTransaction("Income")

        }


        viewModelScope.launch(Dispatchers.IO) {
            highestTransactionExp = repository.getHighestPayment("Expense")
        }.start()

        viewModelScope.launch(Dispatchers.IO) {
            LowestTransaction = repository.getHighestPayment("Income")
        }.start()
        isLoadedRangeHigh.value = true
        isLoadedRangeLow.value = true
        isLoadingG.value = true

    }


    fun getHighestTransaction(type: String): List<Transactions> {


        return highestTransactionExp
    }

    fun getLowestTransaction(type: String): List<Transactions> {


        return LowestTransaction
    }


}