package com.bharath.expensetracker.database.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bharath.expensetracker.database.data.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class NameDB :RoomDatabase(){
    abstract fun userDao():DaoName
}