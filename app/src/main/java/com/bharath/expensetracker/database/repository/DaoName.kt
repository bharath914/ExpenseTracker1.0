package com.bharath.expensetracker.database.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bharath.expensetracker.database.data.User

@Dao
interface DaoName {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertName(user:User)
        @Query("SELECT name_of_User FROM Name_Table")
        suspend fun getName():String
}