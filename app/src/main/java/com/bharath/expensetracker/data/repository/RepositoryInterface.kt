package com.bharath.expensetracker.data.repository

import com.bharath.expensetracker.data.model.Transactions
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {

    suspend fun insertT(transactions: Transactions)
//    suspend fun deleteT(transactions: Transactions)
//    suspend fun updateT()
     fun getTransactions():Flow<List<Transactions>>
}