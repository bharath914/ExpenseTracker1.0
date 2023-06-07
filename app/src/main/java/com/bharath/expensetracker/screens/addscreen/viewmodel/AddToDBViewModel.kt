package com.bharath.expensetracker.screens.addscreen.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.data.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddToDBViewModel @Inject constructor (
        private val repository: RepositoryInterface,


):ViewModel() {

    var isLoading= mutableStateOf(false)

//    var list=repository.getTransactions()
    fun saveToDb(transactions: Transactions){

        val job=viewModelScope.launch {
            Log.d("TAG","started")
            repository.insertT(transactions)
            }
        job.start()
        if (job.isCompleted){
            Log.d("TAG","successfully inserted")
        }
    }

    }
