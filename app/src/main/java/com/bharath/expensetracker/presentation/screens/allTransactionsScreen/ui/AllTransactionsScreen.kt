package com.bharath.expensetracker.presentation.screens.allTransactionsScreen.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.common.Cons
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.screens.allTransactionsScreen.ui.components.AtsCard
import com.bharath.expensetracker.screens.allTransactionsScreen.ui.components.Rd_Card
import com.bharath.expensetracker.screens.allTransactionsScreen.viewmodel.ATSViewModel
import com.bharath.expensetracker.screens.settings.viewmodel.SettingsVm
import com.bharath.expensetracker.ui.theme.Allura
import com.bharath.expensetracker.ui.theme.Inter_Bold
import com.bharath.expensetracker.presentation.uielements.NoExpense
import com.bharath.expensetracker.presentation.uielements.NoIncome
import com.bharath.expensetracker.presentation.uielements.NothingHere
import com.bharath.expensetracker.presentation.uielements.RecentlyDeleted
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(
    ExperimentalPagerApi::class,
    ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@Composable
@Preview
fun AllTransactionsScreen(
    atsViewModel: ATSViewModel = hiltViewModel(),
) {
    val settingsVm: SettingsVm = hiltViewModel()
    val colorBlock by remember {
        mutableStateOf(!settingsVm.colorBlocks.value)
    }
    val scrollOptions by remember {
        mutableStateOf(settingsVm.pagerOption.value)
    }
    val state = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var shouldEdit by remember { mutableStateOf(false) }


    var list1 = atsViewModel.allTcs.collectAsState(initial = emptyList()).value




    val list2 = atsViewModel.dynamicExpenseList.collectAsState(initial = emptyList<Transactions>()).value
    val list3 = atsViewModel.allIncomes.collectAsState(initial = emptyList()).value
    val list4 = atsViewModel.getAllRd().collectAsState(initial = emptyList()).value
    val isLoadingExpenses = atsViewModel.collectall.collectAsState()
//    listTs.add(list1[0])

    var t by remember {
        mutableStateOf(
            Transactions(
                "", 0f, "", "", "", "", "", "", 1
            )
        )
    }

    Scaffold { it ->
        it
        ModalBottomSheetLayout(
            sheetState = state, sheetShape = RoundedCornerShape(10), sheetElevation = 25.dp,

            sheetContent = {

                AnimatedVisibility(visible = shouldEdit) {

                    EditScreenAts(t = t) {
                        scope.launch {

                            state.hide()
                        }
                    }

                }
            }, sheetBackgroundColor = MaterialTheme.colorScheme.primaryContainer
        ) {


            Surface(color = MaterialTheme.colorScheme.surface) {

                var tabIndex by remember { mutableStateOf(0) }

                val pagerState = rememberPagerState()
                val coroutineScope = rememberCoroutineScope()
                val tabs = listOf(
                    "All", "Expenses", "Income", "Recently Deleted"
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 70.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()

                                .background(MaterialTheme.colorScheme.surface)
                        ) {
                            Row {


                                Text(
                                    text = "History", fontSize = 55.sp, fontFamily = Allura,

                                    color = MaterialTheme.colorScheme.primary, modifier = Modifier

                                        .weight(6f)


                                )


                            }
                        }
//                        Text(
//                            text = "If it lags, Turn off  Scrollable tabs in settings ",
//                            fontSize = 12.sp,
//                            modifier = Modifier
//                                .padding(10.dp)
//                                .alpha(0.6f)
//                        )


                        ScrollableTabRow(

                            selectedTabIndex = tabIndex,
                            modifier = Modifier.fillMaxWidth(),
                            containerColor = MaterialTheme.colorScheme.surface,
                            edgePadding = 10.dp,


                            ) {

                            tabs.forEachIndexed { tab_index, tab ->
                                Tab(
                                    selected = tabIndex == tab_index,
                                    onClick = {


                                        tabIndex = tab_index
                                        coroutineScope.launch {
                                            pagerState.scrollToPage(tabIndex)

                                        }


                                    },

                                    text = {
                                        Text(
                                            text = tab,
                                            fontSize = 15.sp,
                                            fontFamily = Inter_Bold,
                                            modifier = Modifier.alpha(0.7f)
                                        )
                                    },
                                    selectedContentColor = MaterialTheme.colorScheme.primary,
                                    unselectedContentColor = MaterialTheme.colorScheme.inverseSurface
                                )

                            }
                        }


                        var isLoading by remember {


                            mutableStateOf(false)
                        }



                        HorizontalPager(
                            count = 4, modifier = Modifier.fillMaxSize(), state = pagerState,

                            verticalAlignment = Alignment.Top,

                        ) {


//                            AnimatedContent(targetState = pagerState.currentPage, transitionSpec = {
//                                fadeIn()with fadeOut()
//
//
//                            }
//
//                            )
//
//                            {


                                when (currentPage) {
                                    0 -> {
                                        if (list1.isEmpty()) {
//                                        CustomList(list = list1)
                                            AnimatedVisibility(visible = true) {

                                                NothingHere()
                                            }
                                        } else {


                                            LazyColumn {

                                                items(list1) { it ->
//
                                                    val modifier = Modifier
                                                    AtsCard(
                                                        detail = it,
                                                        modifier = modifier,
                                                        onclick = {

                                                            t = it
                                                            shouldEdit = true
                                                            scope.launch { state.show() }
                                                        },
                                                        colorOfCategory = Cons.colorMap[it.category]!!,
                                                        showColorBlock = colorBlock
                                                    )

                                                }

                                            }
                                        }
                                        tabIndex = currentPage

                                    }

                                    1 -> {
//                                        CustomList(list = list2)
                                        tabIndex = currentPage
                                        if (isLoadingExpenses.value){
                                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                                                LinearProgressIndicator()
                                            }
                                        }else{
                                        if (list2.isEmpty()) {
//                                        CustomList(list = list1)
                                            AnimatedVisibility(visible = true) {

                                                NoIncome()
                                            }
                                        } else {


                                            LazyColumn {

                                                items(list2) {
//
                                                    val modifier = Modifier
                                                    AtsCard(
                                                        detail = it,
                                                        modifier = modifier,
                                                        onclick = {

                                                            t = it
                                                            shouldEdit = true
                                                            scope.launch {
                                                                state.show()
                                                            }
                                                        },
                                                        colorOfCategory = Cons.colorMap[it.category]!!,
                                                        showColorBlock = colorBlock
                                                    )

                                                }

                                            }
                                        }

                                    }
                                    }

                                    2 -> {
//                                        CustomList(list = list3)
                                        if (list3.isEmpty()) {
//                                        CustomList(list = list1)
                                            AnimatedVisibility(visible = true) {

                                                NoExpense()
                                            }
                                        } else {


                                            LazyColumn {

                                                items(list3) { it ->
//
                                                    val modifier = Modifier
                                                    AtsCard(
                                                        detail = it,
                                                        modifier = modifier,
                                                        onclick = {

                                                            t = it
                                                            shouldEdit = true
                                                            scope.launch { state.show() }
                                                        },
                                                        colorOfCategory = Cons.colorMap[it.category]!!,
                                                        showColorBlock = colorBlock
                                                    )

                                                }

                                            }
                                        }
                                        tabIndex = currentPage
                                    }

                                    3 -> {
                                        RD_CustomList(list = list4)
                                        tabIndex = currentPage
                                    }
                                }

//                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun RD_CustomList(list: List<Transactions>) {
    if (list.isEmpty()) {
        AnimatedVisibility(visible = true) {

            RecentlyDeleted()
        }
    } else {


        LazyColumn {
            items(list) {

                val modifier = Modifier
                Rd_Card(detail = it, modifier = modifier)

            }

        }
    }

}





