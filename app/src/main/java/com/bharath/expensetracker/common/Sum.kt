package com.bharath.expensetracker.common

import androidx.room.ColumnInfo
import com.bharath.expensetracker.data.model.DbConst

data class Sum(
   @ColumnInfo(DbConst.month) val Month:String,
    @ColumnInfo(DbConst.amount)val Amount:String
)
