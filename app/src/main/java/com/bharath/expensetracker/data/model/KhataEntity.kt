package com.bharath.expensetracker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bharath.expensetracker.data.model.KhataEntity.Companion.name
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(
    tableName = name
)
@Serializable
data class KhataEntity(
    @PrimaryKey(autoGenerate = true)
    @SerialName("id")
    val id: Int = 0,

    @SerialName("nameOfThePerson")
    @ColumnInfo("nameOfThePerson")
    val nameOfThePerson: String = "",

    @SerialName("amount")
    @ColumnInfo("amount")
    val amount: String = "",

    @SerialName("debtOrCredit")
    @ColumnInfo("debtOrCredit")
    val debtOrCredit: Boolean = false,  // false -> debit , true -> credit

    @SerialName("dateAdded")
    @ColumnInfo("dateAdded")
    val dateAdded: String = "",


    @SerialName("time")
    @ColumnInfo("time")
    val time: String = "",


    @SerialName("reason")
    @ColumnInfo("reason")
    val reason: String = "",


    @SerialName("accountName")
    @ColumnInfo("accountName")
    val accountName: String = "",

    @SerialName("paymentMethod")
    @ColumnInfo("paymentMethod")
    val paymentMethod: String = "",


    ) {
    companion object {
        const val name = "KhataEntity"
    }
}
