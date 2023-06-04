package com.bharath.expensetracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Transactions(
    val descriptionOfPayment:String,
    val amount : String,
    val type:String,
    val category:String,
    val time:String,
    val date:String,

    @PrimaryKey  val id :Int ?=null
)
