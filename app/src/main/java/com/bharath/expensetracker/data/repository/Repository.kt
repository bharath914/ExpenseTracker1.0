package com.bharath.expensetracker.data.repository

import com.bharath.expensetracker.data.dao.DaoDb
import com.bharath.expensetracker.data.model.Transactions
import kotlinx.coroutines.flow.Flow

class Repository    (
    private val dao: DaoDb
        ) :RepositoryInterface{
    override suspend fun insertT(transactions: Transactions) {
        dao.insertTransaction(transaction = transactions)
    }

//    override suspend fun deleteT(transactions: Transactions) {
//        dao.deleteTransaction()
//    }
//
//    override suspend fun updateT() {
//        dao.updateTransaction()
//    }

    override  fun getTransactions(): Flow<List<Transactions>> {
        return dao.getTransactions()
    }
}