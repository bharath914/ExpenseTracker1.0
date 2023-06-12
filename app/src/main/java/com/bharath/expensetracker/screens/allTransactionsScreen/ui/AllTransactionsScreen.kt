package com.bharath.expensetracker.screens.allTransactionsScreen.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.screens.allTransactionsScreen.ui.components.AtsCard
import com.bharath.expensetracker.screens.allTransactionsScreen.ui.components.Rd_Card
import com.bharath.expensetracker.screens.allTransactionsScreen.viewmodel.ATSViewModel
import com.bharath.expensetracker.ui.theme.Inter_Bold
import com.bharath.expensetracker.uielements.NothingHere
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
@Preview
fun AllTransactionsScreen(
    atsViewModel: ATSViewModel = hiltViewModel(),
) {
    Surface(color = MaterialTheme.colorScheme.background) {

        var tabIndex by remember { mutableStateOf(0) }
        var tabselected by remember { mutableStateOf(false) }
        val pagerState = rememberPagerState()
        val coroutineScope= rememberCoroutineScope()
        val tabs = listOf(
            "All", "Expenses",
            "Income",
            "Recently Deleted"
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
                        .wrapContentHeight()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Row {


                        Text(
                            text = "History", fontSize = 45.sp,
                            fontFamily = Inter_Bold,
                            letterSpacing = 1.sp,
                            color = MaterialTheme.colorScheme.inversePrimary,
                            modifier = Modifier
                                .padding(10.dp)
                                .weight(6f)


                        )


                    }
                }

                ScrollableTabRow(

                    selectedTabIndex = tabIndex,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    edgePadding = 10.dp,


                    ) {

                    tabs.forEachIndexed { tabindex, tab ->
                        Tab(
                            selected = tabIndex == tabindex,
                            onClick = {



                                    tabIndex = tabindex
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


                val list1 = atsViewModel.allPays.collectAsState(initial = emptyList()).value
                val list2 =
                    atsViewModel.getAllExpensesAts().collectAsState(initial = emptyList()).value
                val list3 =
                    atsViewModel.getAllIncomeAts().collectAsState(initial = emptyList()).value
                val list4 = atsViewModel.getAllRd().collectAsState(initial = emptyList()).value
                isLoading = true


                HorizontalPager(
                    count = 4,
                    modifier = Modifier.fillMaxSize(), state = pagerState,

                    verticalAlignment = Alignment.Top
                )
                {


                    AnimatedContent(
                        targetState = pagerState.currentPage,
                        transitionSpec = {
                            slideInVertically() with fadeOut()


                        }

                    )

                    {


                        when (currentPage) {
                            0 -> {
                                CustomList(list = list1)
                                tabIndex = currentPage

                            }

                            1 -> {
                                CustomList(list = list2)
                                tabIndex = currentPage
                            }

                            2 -> {
                                CustomList(list = list3)
                                tabIndex = currentPage
                            }

                            3 -> {
                                RD_CustomList(list = list4)
                                tabIndex = currentPage
                            }
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

            NothingHere()
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

@Composable
fun CustomList(list: List<Transactions>) {

    LazyListPay(list)

}

@Composable
fun LazyListPay(list: List<Transactions>) {
    if (list.isEmpty()) {
        AnimatedVisibility(visible = true) {

            NothingHere()
        }
    } else {


        LazyColumn {
            items(list) {
//
                val modifier = Modifier
                AtsCard(detail = it, modifier = modifier)

            }

        }
    }
}

@Composable
fun SetUpNavGraph(navHostController: NavHostController, t: Transactions) {
    NavHost(navController = navHostController,
        startDestination = "Edit_Screen",
        builder = {
            composable("Edit_Screen") {
                EditScreenAts(t = t)
            }
        })
}
