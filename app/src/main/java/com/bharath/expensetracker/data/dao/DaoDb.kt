package com.bharath.expensetracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bharath.expensetracker.data.model.Transactions
import kotlinx.coroutines.flow.Flow


@Dao
interface DaoDb
{
    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transactions)




    @Delete
    suspend fun delete(transaction: Transactions)
    @Query("SELECT EXISTS(SELECT 1 FROM transactions where Type =:typeSpend)")
    fun checkDbiSEmptyOrNot(typeSpend: String):Flow<Int>
    @Query("SELECT * FROM transactions Order by id DESC ")
    fun getEveryTransactions():Flow<List<Transactions>>

    @Query("SELECT SUM(Amount) FROM transactions WHERE Type =:type and Category =:category and Month =:month")
    fun checkCategorySum(category:String,month:String,type:String):Float
    @Query("SELECT * FROM transactions WHERE Type =:typeSpend ORDER BY id DESC LIMIT 7")
    fun getFewCustomTransactions(typeSpend: String):Flow<List<Transactions>>

    @Query("SELECT * FROM transactions   WHERE Type =:typeSpend Order by id DESC" )
    fun getCustomTransaction(typeSpend:String):Flow<List<Transactions>>

    @Query("SELECT SUM(Amount) From transactions Where Type =:exp and Month =:currentMonth")
    fun getSumOfPayments(exp:String,currentMonth:String):Flow<Float>
    @Query("SELECT * FROM transactions WHERE Type =:typeSpend ORDER BY Amount DESC LIMIT 1")
    fun getHighestPayment(typeSpend: String):List<Transactions>

    @Query("SELECT * FROM transactions WHERE Type =:typeSpend ORDER BY Amount ASC LIMIT 1")
    fun getLowestPayment(typeSpend: String):List<Transactions>



}