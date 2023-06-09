package com.bharath.expensetracker.screens.viewmodel

import androidx.compose.runtime.MutableState
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
class HomeViewModel @Inject constructor(
    private val repository: RepositoryInterface,
) : ViewModel() {

    var expensesList: Flow<List<Transactions>> = emptyFlow()
    var incomeList: Flow<List<Transactions>> = emptyFlow()
    var incomeSumArr: Flow<Float>? = emptyFlow()
    var expSumArr: Flow<Float>? = emptyFlow()
    private var allTransactions: Flow<List<Transactions>> = emptyFlow()
    var isLoadingWhileFetching: MutableState<Boolean> = mutableStateOf(true)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            expensesList = repository.getFewCustomTransactions("Expense")
        }
        viewModelScope.launch(Dispatchers.IO) {
            incomeList = repository.getFewCustomTransactions("Income")
        }
        viewModelScope.launch(Dispatchers.Default) {
            incomeSumArr = repository.getSumOfTransaction("Income")

        }
        viewModelScope.launch(Dispatchers.Default) {
            expSumArr = repository.getSumOfTransaction("Expense")
        }
        isLoadingWhileFetching.value = false

    }

    fun getAllTransactionsHome(): Flow<List<Transactions>> {
        val job = viewModelScope.launch {
//            allTransactions=repository.getTransactions()
        }
        job.start()
        if (job.isCompleted) {
            isLoadingWhileFetching.value = false
        }


        return allTransactions

    }

    fun getCustomTransaction(type: String): Flow<List<Transactions>> {
        if (type == "Expense") {
            return expensesList
        }
        return incomeList
    }

    fun getUpdtSums() {
        expSumArr = repository.getSumOfTransaction("Expense")
        incomeSumArr = repository.getSumOfTransaction("Income")
    }

    fun checkExpisEmpty(): Flow<Int> {
        var i = emptyFlow<Int>()
        viewModelScope.launch(Dispatchers.IO) {
            i = repository.checkIsEmpty(
                "Expense"
            )
        }
        return i
    }

    fun checkIncisEmpty(): Flow<Int> {
        var i = emptyFlow<Int>()
        viewModelScope.launch(Dispatchers.IO) {
            i = repository.checkIsEmpty(
                "Income"
            )
        }
        return i
    }
}