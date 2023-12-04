package com.bharath.expensetracker.presentation.screens.settings.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.expensetracker.common.ColorSchemes
import com.bharath.expensetracker.common.Cons
import com.bharath.expensetracker.data.deleted.Rd_REpo
import com.bharath.expensetracker.data.repository.RepositoryInterface
import com.bharath.expensetracker.data.datastore.DataStorePref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsVm @Inject constructor(
    private val dataStorePref: DataStorePref,
    private val repository: RepositoryInterface,
    private val rdRepo: Rd_REpo,
) : ViewModel() {

    private var _amoledTheme = mutableStateOf(false)

    val amoledTheme: State<Boolean> = _amoledTheme

    private var _monthlyCalendar = mutableStateOf(false)

    val monthlyCalendar: State<Boolean> = _monthlyCalendar
    private var _ColorBlocks = mutableStateOf(false)

    val colorBlocks: State<Boolean> = _ColorBlocks


    private var _pagerOption = mutableStateOf(false)

    val pagerOption: State<Boolean> = _pagerOption


    private var _getThemeColor = mutableStateOf(Cons.defaultColorSchme)

    val getThemeColor: State<ColorSchemes> = _getThemeColor

    init {
        viewModelScope.launch(Dispatchers.IO) {

            dataStorePref.getAmoledStatus().collectLatest {
                _amoledTheme.value = it
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            dataStorePref.readColor().collectLatest {
                _getThemeColor.value = Cons.ColorSchemes[it]
            }
        }


        viewModelScope.launch(Dispatchers.IO) {

            dataStorePref.readCalendarStyle().collectLatest { boo ->
                _monthlyCalendar.value = boo
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            dataStorePref.getColorBlockStatus().collectLatest {
                _ColorBlocks.value = it
            }
        }
        viewModelScope.launch (Dispatchers.IO){
            dataStorePref.readPagerOption().collectLatest {
                _pagerOption.value = it
            }
        }


    }

    fun saveColorBlockState(bool: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStorePref.colorBlock(bool)
        }
    }

    fun saveCalendarState(bool: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStorePref.saveCalendarStyle(bool)
        }
    }

    fun savePagerOption(bool: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            dataStorePref.savePagerOption(bool = bool)
        }
    }

    fun saveAmoledStatus(bool: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStorePref.amoledTheme(bool)
        }
    }

    fun dropAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.dropALl()
        }
        viewModelScope.launch(Dispatchers.IO)
        {
            repository.dropALl()
        }
    }


    fun saveCustomColor(num: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStorePref.saveColor(num)
        }
    }


}

