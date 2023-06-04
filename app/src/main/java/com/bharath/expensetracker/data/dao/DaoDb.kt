package com.bharath.expensetracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bharath.expensetracker.data.model.Transactions
import kotlinx.coroutines.flow.Flow


@Dao
interface DaoDb
{
    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transactions)

//    @Update
//    suspend fun updateTransaction()
//    @Delete
//    suspend fun deleteTransaction()

    @Query("SELECT * FROM transactions")
    fun getTransactions():Flow<List<Transactions>>

}