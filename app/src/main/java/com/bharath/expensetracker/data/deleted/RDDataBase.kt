package com.bharath.expensetracker.data.deleted

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bharath.expensetracker.data.model.Transactions


@Database(
    entities = [Transactions::class],
    version = 1
)
abstract class RDDataBase :RoomDatabase() {
    abstract val daoRd:Rd_dao
}