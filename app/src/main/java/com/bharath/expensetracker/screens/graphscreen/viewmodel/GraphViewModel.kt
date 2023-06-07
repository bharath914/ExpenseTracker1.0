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
    private val repository: RepositoryInterface
) :ViewModel() {

    var expenseSum :Flow<Float> = emptyFlow()
    var incomeSum :Flow<Float> = emptyFlow()
    var isLoadingG = mutableStateOf(false)
    var highestTransactionExp:Flow<Float> = emptyFlow()
    var highestTransactionDetail:Flow<Transactions> = emptyFlow()


    init {

        val j=viewModelScope.launch (Dispatchers.IO){
            expenseSum =repository.getSumOfTransaction("Expense")
            incomeSum=repository.getSumOfTransaction("Income")

        }



            isLoadingG.value=true

    }


    fun getHighestTransaction(type:String):Flow<Float>{


        val k =viewModelScope.launch(Dispatchers.IO){
            highestTransactionExp =repository.getHighestPayment(type)

        }
        return highestTransactionExp
    }
    fun getHighestTransactionDetail(float: Float):Flow<Transactions>{
        viewModelScope.launch(Dispatchers.IO) {
//            highestTransactionDetail =repository.getHighestPaymentDetail(float)
        }
        return highestTransactionDetail
    }




}