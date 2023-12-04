package com.bharath.expensetracker.presentation.events

sealed class KhataAddEvents {
    data class onTitleEntered(val title: String) : KhataAddEvents()
    data class onDescEntered(val desc: String) : KhataAddEvents()
    data class onDateEntered(val date: String) : KhataAddEvents()
    data class onAmountEntered(val amount: String) : KhataAddEvents()
    data class isDebitChanged(val bool: Boolean) : KhataAddEvents()

    data class onPaymentModeEntered(val mop: String) : KhataAddEvents()
    data class onChecked(val isNewNote: Boolean) : KhataAddEvents()
    data class onBackPressed(val isNewNote: Boolean) : KhataAddEvents()

}
