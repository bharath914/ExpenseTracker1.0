package com.bharath.expensetracker.presentation.screens.khata.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.expensetracker.data.model.KhataEntity
import com.bharath.expensetracker.domain.usecases.insertIntoKhataUseCase
import com.bharath.expensetracker.domain.usecases.updateIntoKhataUseCase
import com.bharath.expensetracker.presentation.events.KhataAddEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class KhataAddViewModel @Inject constructor(
    private val insertIntoKhataUseCase: insertIntoKhataUseCase,
    private val updateIntoKhataUseCase: updateIntoKhataUseCase,

    ) : ViewModel() {

    private val _titleName = MutableStateFlow("")
    val titleName = _titleName.asStateFlow()

    private val _amount = MutableStateFlow("")
    val amount = _amount.asStateFlow()

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    private val _isDebit = MutableStateFlow(false)
    val isDebit = _isDebit.asStateFlow()

    private val _date = MutableStateFlow("")
    val date = _date.asStateFlow()

    private val _accountName = MutableStateFlow("")
    val accountName = _accountName.asStateFlow()

    private val _paymentMethod = MutableStateFlow("")
    val paymentMethod = _paymentMethod.asStateFlow()

    private val oldKhataEntity = mutableStateOf(KhataEntity())
    fun setKhataEntity(khataEntity: KhataEntity) {
        oldKhataEntity.value = khataEntity
    }






    fun onEvent(event: KhataAddEvents) {

        when (event) {

            is KhataAddEvents.onTitleEntered -> {
                _titleName.tryEmit(event.title)
            }

            is KhataAddEvents.onAmountEntered -> {
                _amount.tryEmit(event.amount)
            }

            is KhataAddEvents.isDebitChanged -> {
                _isDebit.tryEmit(event.bool)
            }

            is KhataAddEvents.onDateEntered -> {
                _date.tryEmit(event.date)
            }

            is KhataAddEvents.onDescEntered -> {
                _description.tryEmit(event.desc)
            }

            is KhataAddEvents.onChecked -> {
                saveToDb(event.isNewNote)
            }

            is KhataAddEvents.onBackPressed -> {
                saveToDb(event.isNewNote)
            }

            is KhataAddEvents.onPaymentModeEntered -> {
                _paymentMethod.tryEmit(event.mop)
            }
        }

    }


    fun saveToDb(isNewNote: Boolean) {

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val time = dateFormat.format(calendar.time)

        viewModelScope.launch(IO) {

            if (isNewNote) {

                insertIntoKhataUseCase.invoke(
                    KhataEntity(
                        nameOfThePerson = _titleName.value,
                        amount = _amount.value,
                        debtOrCredit = _isDebit.value,
                        dateAdded = _date.value,
                        time = time,
                        reason = _description.value,
                        accountName = _accountName.value,
                        paymentMethod = _paymentMethod.value
                    )
                )
            } else {
                updateIntoKhataUseCase.invoke(
                    KhataEntity(
                        id = oldKhataEntity.value.id,
                        nameOfThePerson = _titleName.value,
                        amount = _amount.value,
                        debtOrCredit = _isDebit.value,
                        dateAdded = _date.value,
                        time = time,
                        reason = _description.value,
                        accountName = _accountName.value,
                        paymentMethod = _paymentMethod.value
                    )
                )
            }
        }


    }


}