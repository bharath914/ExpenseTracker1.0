package com.bharath.expensetracker.data.deleted

import com.bharath.expensetracker.data.model.Transactions
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Rd_RepoImpl @Inject constructor(
    private val dao:Rd_dao
) :Rd_REpo {
    override suspend fun insert(transactions: Transactions) {
        dao.insert(transactions)
    }

    override suspend fun delete(transactions: Transactions) {
        dao.delete(transactions)
    }

    override suspend fun getAllData(): Flow<List<Transactions>> {
      return  dao.getAllData()
    }
}