package com.bharath.expensetracker.database.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity("Name_Table")
data class User(
    @PrimaryKey (autoGenerate = true) val Uid:Int,
    @ColumnInfo(name="name_of_User") val name:String?
)