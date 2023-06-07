package com.bharath.expensetracker.data.repository

import com.bharath.expensetracker.data.dao.DaoDb
import com.bharath.expensetracker.data.model.Transactions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull

class Repository    (
    private val dao: DaoDb
        ) :RepositoryInterface{
    override suspend fun insertT(transactions: Transactions) {
        dao.insertTransaction(transaction = transactions)
    }

    override suspend fun deleteT(transactions: Transactions) {
        dao.delete(transactions)
    }


    override  fun getTransactions(): Flow<List<Transactions>> {
        return dao.getEveryTransactions()
    }

    override fun getCustomTransactions(type: String): Flow<List<Transactions>> {
        return dao.getCustomTransaction(typeSpend = type)
    }

    override fun getFewCustomTransactions(typeSpend: String): Flow<List<Transactions>> {
       return dao.getFewCustomTransactions(typeSpend)
    }

    override fun getSumOfTransaction(type: String):Flow< Float> {
        return dao.getSumOfPayments(type).filterNotNull()
    }

    override suspend fun checkIsEmpty(type: String): Flow<Int> {
        return dao.checkDbisEmptyorNot(type)
    }

    override suspend fun getHighestPayment(type: String): Flow<Float> {
        return dao.getHighestPayment(type).filterNotNull()
    }

//    override suspend fun getHighestPaymentDetail(float: Float): Flow<Transactions> {
//        return dao.getHighestPaymentDetail(float)
//    }
}