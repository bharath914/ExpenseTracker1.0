package com.bharath.expensetracker.presentation.screens.allTransactionsScreen.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.expensetracker.data.deleted.Rd_REpo
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.data.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ATSViewModel
@Inject constructor(
    private val repository: RepositoryInterface,
    private val rd_repository: Rd_REpo
) : ViewModel() {

    private val _allExpenses = MutableStateFlow(listOf<Transactions>())
    var allExpenses: StateFlow<List<Transactions>> = _allExpenses

    private val _allIncomes = MutableStateFlow(listOf<Transactions>())
    var allIncomes: Flow<List<Transactions>> = _allIncomes

    var allPays: Flow<List<Transactions>> = emptyFlow()
        private set

    private var _allPaysTcs = mutableStateOf(emptyList<Transactions>())
    var allPaysTcs : State<List<Transactions>> = _allPaysTcs
    var RdAllPays: Flow<List<Transactions>> = emptyFlow()
        private set
    private val _allTCS = MutableStateFlow(listOf<Transactions>())
    var allTcs: Flow<List<Transactions>> = _allTCS


    private var _collectall_= MutableStateFlow(false)
    var collectall  = _collectall_.asStateFlow()

    var list:List<Transactions> = emptyList()




    private val job = viewModelScope.launch(Dispatchers.IO) {
//     repository.getTransactions().collectLatest {
//         _allPaysTcs.value = it
//     }
//        allPays = repository.getTransactions()
    _allTCS.emitAll(repository.getTransactions())
    }


    @OptIn(FlowPreview::class)
    val dynamicExpenseList = _allTCS.onEach {
_collectall_.tryEmit(true)
    }.debounce(200)
        .combine(_allTCS){tcs , e->
            tcs.filter {
                it.type == "Expense"
            }
        }
        .onEach { _collectall_.update { false } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(1200),_allTCS.value)
    init {

        job.start()
        viewModelScope.launch(Dispatchers.IO){

             _allTCS.collectLatest {

                 if (it.isNotEmpty()){_collectall_.tryEmit(true)}
            }
        }
//        viewModelScope.launch(Dispatchers.IO) {
//            if (collectall_.value){
//            _allTCS.collectLatest {
//
//               _allExpenses.tryEmit(  it.filter {
//                   it.type == "Expense"
//               })
//                _allIncomes.tryEmit(it.filter {
//                    it.type=="Income"
//                })
//            }}
//        }

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