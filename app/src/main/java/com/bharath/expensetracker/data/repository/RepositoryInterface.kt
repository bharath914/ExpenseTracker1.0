package com.bharath.expensetracker.data.repository

import com.bharath.expensetracker.data.model.Transactions
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {

    suspend fun insertT(transactions: Transactions)
    suspend fun deleteT(transactions: Transactions)

    suspend fun dropALl()
     fun getTransactions():Flow<List<Transactions>>
     fun getCustomTransactions(type:String):Flow<List<Transactions>>
    fun getFewCustomTransactions(typeSpend: String):Flow<List<Transactions>>
     fun getSumOfTransaction(type:String,currentMonth:String):Flow<Float>
     suspend fun checkIsEmpty(type: String):Flow<Int>
     suspend fun getHighestPayment(type: String):List<Transactions>
     suspend fun getLowestPayment(type: String):List<Transactions>
     suspend fun getCategorySum(category:String,month:String,type: String):Float
//     suspend fun getHighestPaymentDetail(float: Float):Flow<Transactions>

}