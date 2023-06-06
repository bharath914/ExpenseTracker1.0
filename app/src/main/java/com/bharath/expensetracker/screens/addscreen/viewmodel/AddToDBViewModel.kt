package com.bharath.expensetracker.screens.addscreen.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.data.repository.Repository
import com.bharath.expensetracker.data.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.forEach
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
