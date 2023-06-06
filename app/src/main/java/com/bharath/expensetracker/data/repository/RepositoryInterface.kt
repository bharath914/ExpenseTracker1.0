package com.bharath.expensetracker.data.repository

import androidx.compose.runtime.MutableState
import com.bharath.expensetracker.data.model.Transactions
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {

    suspend fun insertT(transactions: Transactions)
    suspend fun deleteT(transactions: Transactions)

     fun getTransactions():Flow<List<Transactions>>
     fun getCustomTransactions(type:String):Flow<List<Transactions>>
    fun getFewCustomTransactions(typeSpend: String):Flow<List<Transactions>>
     fun getSumOfTransaction(type:String):Flow<Float>
     suspend fun checkIsEmpty(type: String):Flow<Int>
}