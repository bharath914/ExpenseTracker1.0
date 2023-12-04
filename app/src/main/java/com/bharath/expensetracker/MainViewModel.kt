package com.bharath.expensetracker


import androidx.lifecycle.ViewModel
import com.bharath.expensetracker.data.model.KhataEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {

    private val _existingKhataAccount = MutableStateFlow(KhataEntity())
    val existingKhataAccount = _existingKhataAccount.asStateFlow()
    fun setExistingAccount(khataEntity: KhataEntity) {
        _existingKhataAccount.tryEmit(khataEntity)
    }


}