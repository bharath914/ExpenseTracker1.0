package com.bharath.expensetracker.screens.graphscreen.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bharath.expensetracker.data.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class GraphViewModel @Inject constructor(
    private val repository: RepositoryInterface
) :ViewModel() {

   private var _isLoading = MutableLiveData<Boolean>(true)
    var isLoading:LiveData<Boolean> =_isLoading



















}