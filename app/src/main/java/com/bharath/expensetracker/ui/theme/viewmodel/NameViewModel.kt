package com.bharath.expensetracker.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.expensetracker.database.data.User
import com.bharath.expensetracker.database.repository.repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NameViewModel @Inject constructor(
   private val repository: repository
) : ViewModel() {



    suspend fun insertName(name:String){

        viewModelScope.launch {
            repository.insertName(User(
                1,
                name=name
            ))
        }
        }
    fun GetName():String{
        var name=""
        viewModelScope.launch {
          name=  getName()
        }
        return name
    }
    private suspend fun getName():String{
        var name=""
        viewModelScope.launch {
             name=repository.getName()

        }
        return name
    }
}