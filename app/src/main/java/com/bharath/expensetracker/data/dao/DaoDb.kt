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
  fun checkDbisEmptyorNot(typeSpend: String):Flow<Int>
    @Query("SELECT * FROM transactions Order by id DESC ")
    fun getEveryTransactions():Flow<List<Transactions>>

    @Query("SELECT * FROM transactions WHERE Type =:typeSpend ORDER BY id DESC LIMIT 7")
    fun getFewCustomTransactions(typeSpend: String):Flow<List<Transactions>>

    @Query("SELECT * FROM transactions   WHERE Type =:typeSpend Order by id DESC" )
    fun getCustomTransaction(typeSpend:String):Flow<List<Transactions>>

    @Query("SELECT SUM(Amount) From transactions Where Type =:exp")
    fun getSumOfPayments(exp:String):Flow<Float>
    @Query("SELECT max(Amount) from transactions Where Type =:typeSpend")
    fun getHighestPayment(typeSpend: String):Flow<Float>

//    @Query("Select * from transactions Order by Amount DESC")
//    fun getHighestPaymentDetail(amount:Float):Flow<Transactions>
    @Query("Select * from transactions Order by Amount DESC")
    fun getDescListofAllTransaction():Flow<List<Transactions>>

    @Query("Select * from transactions Order by Amount Asc")
    fun getAscListOfAllTransaction():Flow<List<Transactions>>


}