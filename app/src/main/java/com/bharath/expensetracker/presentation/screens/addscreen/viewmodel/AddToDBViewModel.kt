package com.bharath.expensetracker.presentation.screens.addscreen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.data.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddToDBViewModel @Inject constructor(
    private val repository: RepositoryInterface,


    ) : ViewModel() {


    fun saveToDb(transactions: Transactions) {

        viewModelScope.launch {
            Log.d("TAGInsert", "started")
            repository.insertT(transactions)


        }

    }

}
