package com.bharath.expensetracker.data.repository

import com.bharath.expensetracker.data.dao.DaoDb
import com.bharath.expensetracker.data.model.KhataAccountEntity
import com.bharath.expensetracker.data.model.KhataEntity
import com.bharath.expensetracker.data.model.Transactions
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.withContext

class Repository(
    private val dao: DaoDb,
) : RepositoryInterface {
    override suspend fun insertT(transactions: Transactions) {
        dao.insertTransaction(transaction = transactions)
    }

    override suspend fun deleteT(transactions: Transactions) {
        dao.delete(transactions)
    }

    override suspend fun dropALl() {
        dao.dropAllData()
    }

    override suspend fun getMonthsActive(): Flow<List<String>> {
        return dao.getMonthsActive()
    }

    override suspend fun getMonthlySum(type: String): Flow<List<Float>> {
        return dao.getMonthlySum(type)
    }


    override fun getTransactions(): Flow<List<Transactions>> {
        return dao.getEveryTransactions()
    }

    override fun getCustomTransactions(type: String): Flow<List<Transactions>> {
        return dao.getCustomTransaction(typeSpend = type)
    }

    override fun getFewCustomTransactions(typeSpend: String): Flow<List<Transactions>> {
        return dao.getFewCustomTransactions(typeSpend)
    }

    override fun getSumOfTransaction(type: String, currentMonth: String): Flow<Float> {
        return dao.getSumOfPayments(type, currentMonth).filterNotNull()
    }

    override suspend fun checkIsEmpty(type: String): Flow<Int> {
        return dao.checkDbiSEmptyOrNot(type)
    }

    override suspend fun getHighestPayment(type: String, month: String): List<Transactions> {
        return dao.getHighestPayment(type, month)
    }

    override suspend fun getLowestPayment(type: String, month: String): List<Transactions> {
        return dao.getLowestPayment(type, month)
    }

    override suspend fun getCategorySum(category: String, month: String, type: String): Float {
        return dao.checkCategorySum(category, month, type)
    }

    override suspend fun insertAccountForKhata(khataAccountEntity: KhataAccountEntity) {
        return withContext(IO) {
            dao.insertAccountForKhata(khataAccountEntity)
        }
    }

    override suspend fun deleteAccountForKhata(khataAccountEntity: KhataAccountEntity) {
        return withContext(IO) {
            dao.deleteAccountForKhata(khataAccountEntity)
        }
    }

    override suspend fun updateAccountForKhata(khataAccountEntity: KhataAccountEntity) {
        return withContext(IO) {
            dao.updateAccountForKhata(khataAccountEntity)
        }
    }

    override suspend fun getAllAccounts(): Flow<List<KhataAccountEntity>> {
        return withContext(IO) {
            dao.getAllAccounts()
        }
    }

    override suspend fun insertKhata(khataEntity: KhataEntity) {
        return withContext(IO) {
            dao.insertKhata(khataEntity)
        }
    }

    override suspend fun deleteKhata(khataEntity: KhataEntity) {
        return withContext(IO) {
            dao.deleteKhata(khataEntity)
        }
    }

    override suspend fun updateKhata(khataEntity: KhataEntity) {
        return withContext(IO) {
            dao.updateKhata(khataEntity)
        }
    }

    override suspend fun getAllKhatas(): Flow<List<KhataEntity>> {
        return withContext(IO) {
            dao.getAllKhatas()
        }
    }

//    override suspend fun getHighestPaymentDetail(float: Float): Flow<Transactions> {
//        return dao.getHighestPaymentDetail(float)
//    }
}