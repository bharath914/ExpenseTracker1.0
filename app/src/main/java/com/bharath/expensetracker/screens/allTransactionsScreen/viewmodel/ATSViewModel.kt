package com.bharath.expensetracker.screens.allTransactionsScreen.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.expensetracker.data.deleted.Rd_REpo
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.data.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ATSViewModel
@Inject constructor(
    private val repository: RepositoryInterface,
    private val rd_repository: Rd_REpo
) : ViewModel() {

    var allExpenses: Flow<List<Transactions>> = emptyFlow()
        private set
    var allIncomes: Flow<List<Transactions>> = emptyFlow()
        private set
    var allPays: Flow<List<Transactions>> = emptyFlow()
        private set

    private var _allPaysTcs = mutableStateOf(emptyList<Transactions>())
    var allPaysTcs : State<List<Transactions>> = _allPaysTcs
    var RdAllPays: Flow<List<Transactions>> = emptyFlow()
        private set


    var list:List<Transactions> = emptyList()




    private val job = viewModelScope.launch(Dispatchers.IO) {
//     repository.getTransactions().collectLatest {
//         _allPaysTcs.value = it
//     }
        allPays = repository.getTransactions()
    }

    init {

        job.start()
//        viewModelScope.launch(Dispatchers.IO){
//             allPays.collectLatest {
//                list=it
//            }
//        }
        viewModelScope.launch(Dispatchers.IO) {
            allExpenses = repository.getCustomTransactions("Expense")
        }
        viewModelScope.launch(Dispatchers.IO) {
            allIncomes = repository.getCustomTransactions("Income")
        }
        viewModelScope.launch(Dispatchers.IO){
            RdAllPays=rd_repository.getAllData()
        }
    }


    fun getAllExpensesAts(): Flow<List<Transactions>>
    {


        return allExpenses
    }

    fun getAllIncomeAts(): Flow<List<Transactions>> {

        return allIncomes
    }

    fun deleteTransaction(transactions: Transactions) {
            val bool = insertInRd(transactions)

            viewModelScope.launch {
                repository.deleteT(transactions)
            }

    }

    fun insertInRd(transactions: Transactions): Boolean {
        val jobRd = viewModelScope.launch(Dispatchers.IO) {
            rd_repository.insert(transactions)
        }
        return jobRd.isCompleted


    }
    fun getAllRd():Flow<List<Transactions>>{

        return RdAllPays
    }
    fun deleteRd(transactions: Transactions){
        viewModelScope.launch (Dispatchers.IO){
            rd_repository.delete(transactions)
        }
    }
    fun restoreRd(transactions: Transactions){
        viewModelScope.launch (Dispatchers.IO){
            repository.insertT(transactions)
            rd_repository.delete(transactions)
        }
    }
}