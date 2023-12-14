package com.bharath.expensetracker.presentation.screens.graphscreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.presentation.screens.graphscreen.ui.screens.GraphScreen1
import com.bharath.expensetracker.presentation.screens.graphscreen.ui.screens.GraphScreen2
import com.bharath.expensetracker.presentation.screens.graphscreen.viewmodel.GraphViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import java.time.LocalDate


@OptIn(ExperimentalPagerApi::class)
@Composable
fun GraphScreen(
    graphViewModel: GraphViewModel = hiltViewModel(),
) {

    Surface(color = MaterialTheme.colorScheme.background) {

        val pagerState = rememberPagerState()


        val sumOfIncome = graphViewModel.incomeSum.collectAsState(initial = 0f).value
        val sumOfExpense = graphViewModel.expenseSum.collectAsState(initial = 0f).value
        val currentMonth = "${LocalDate.now().month}"









        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {


            HorizontalPager(
                count = 3,
                state = pagerState,
                itemSpacing = 50.dp
            ) {
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    activeColor = MaterialTheme.colorScheme.primary,
                    inactiveColor = MaterialTheme.colorScheme.inverseSurface
                )

                when (pagerState.currentPage) {
                    0 -> {
                        GraphScreen1(graphViewModel = graphViewModel)
                    }

                    1 -> {
                        if (graphViewModel.isLoadedRangeHigh.value) {
                            GraphScreen2(graphViewModel = graphViewModel,
                                type = "Expense",
                          list=graphViewModel.listCategoriesExp,
                                totalSum = sumOfExpense
                                )
                        }
                    }

                    2 -> {
                        if (graphViewModel.isLoadedRangeLow.value) {
                            GraphScreen2(graphViewModel = graphViewModel,
                                type = "Income",

                                list= graphViewModel.listCategoriesInc,
                                totalSum = sumOfIncome
                            )
                        }
                    }

//                    3 -> {
//                        GraphScreen3()
//                    }

                }


            }
        }
    }
}



