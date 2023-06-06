package com.bharath.expensetracker.data.deleted

import com.bharath.expensetracker.data.model.Transactions
import kotlinx.coroutines.flow.Flow

interface Rd_REpo {
    suspend fun insert(transactions: Transactions)
    suspend fun delete(transactions: Transactions)
    suspend fun getAllData():Flow<List<Transactions>>
}