package com.bharath.expensetracker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Transactions(
    @ColumnInfo(DbConst.description) val descriptionOfPayment: String,
    @ColumnInfo(DbConst.amount) val amount: Float,
    @ColumnInfo(DbConst.type) val type: String,
    @ColumnInfo(DbConst.category) val category: String,
    @ColumnInfo(DbConst.time) val time: String,
    @ColumnInfo(DbConst.date) val date: String,
    @ColumnInfo(DbConst.month) val month: String,
    @ColumnInfo(DbConst.year) val year: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
)
