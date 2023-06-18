package com.bharath.expensetracker.data.deleted

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bharath.expensetracker.data.model.Transactions
import kotlinx.coroutines.flow.Flow

@Dao
interface Rd_dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transactions: Transactions)

    @Delete
    suspend fun delete(transactions: Transactions)
    @Query("delete from Transactions")
    suspend fun dropAllDataRD()

    @Query("select * From transactions")

    fun getAllData():Flow<List<Transactions>>

}