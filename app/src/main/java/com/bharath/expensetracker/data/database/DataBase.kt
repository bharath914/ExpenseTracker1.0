package com.bharath.expensetracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bharath.expensetracker.data.dao.DaoDb
import com.bharath.expensetracker.data.model.KhataAccountEntity
import com.bharath.expensetracker.data.model.KhataEntity
import com.bharath.expensetracker.data.model.Transactions


@Database(
    entities = [Transactions::class, KhataEntity::class, KhataAccountEntity::class],
    version = 1
)
abstract class DataBase : RoomDatabase() {
    abstract val dao: DaoDb
}