package com.bharath.expensetracker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity()
data class KhataAccountEntity(
    @PrimaryKey(true)
    @SerialName("id")
    val id: Int = 0,

    @SerialName("accountName")
    @ColumnInfo("accountName")
    val accountName: String = "",







    ) {

}
