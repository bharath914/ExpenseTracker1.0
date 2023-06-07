package com.bharath.expensetracker.screens.graphscreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.screens.graphscreen.ui.components.PieChart
import com.bharath.expensetracker.screens.graphscreen.util.GraphPagelist
import com.bharath.expensetracker.screens.graphscreen.viewmodel.GraphViewModel
import com.bharath.expensetracker.screens.homescreen.ui.components.CardHome
import com.bharath.expensetracker.ui.theme.Inter_Bold
import com.bharath.expensetracker.ui.theme.Inter_SemiBold
import com.bharath.expensetracker.ui.theme.Lato_Bold
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun GraphScreen(
    graphViewModel: GraphViewModel = hiltViewModel(),
) {

    Surface(color = MaterialTheme.colorScheme.background) {

        var income by remember {
            mutableStateOf(0f)
        }
        var expense by remember {
            mutableStateOf(0f)
        }
        var balance by remember {
            mutableStateOf(0f)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top

        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), contentAlignment = Center
            ) {
                Text(
                    text = "Expense Vs Income",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.alpha(0.8f),
                    fontFamily = Inter_Bold,
                    fontSize = 25.sp
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), contentAlignment = Center
            ) {
                if (graphViewModel.isLoadingG.value) {


                    val incomeFlow = graphViewModel.incomeSum.collectAsState(initial = 0f)
                    val expenseFlow = graphViewModel.expenseSum.collectAsState(initial = 0f)
                    income = incomeFlow.value
//                    Toast.makeText(LocalContext.current, income.toString(), Toast.LENGTH_SHORT).show()
                    expense = expenseFlow.value



                    if (income >= 0 && expense >= 0) {
                        balance = income - expense
                    }

                    PieChart(

                        data = mapOf(
                            Pair("Expense", expense.toInt()),
                            Pair("Balance", balance.toInt()),

                            )
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), contentAlignment = Center
            ) {
                val percentage = (expense / income) * 100
                if (!percentage.isNaN()) {
                    PercentageText(percentage = percentage)

                }


            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 70.dp), contentAlignment = Center
            ) {


                if (graphViewModel.isLoadedRangeHigh.value && graphViewModel.isLoadedRangeLow.value

                ) {
                    if (
                        graphViewModel.LowestTransaction.isNotEmpty()
                        && graphViewModel.highestTransactionExp.isNotEmpty()
                    ){
                    val highest = graphViewModel.getHighestTransaction("")[0]
                    val lowest = graphViewModel.getLowestTransaction("Income")[0]


                    val list = listOf(
                        highest,
                        lowest
                    )
//
           
                    Column(
                        horizontalAlignment = CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
//                   CardHome(detail = highest)
//                      CardHome(detail = lowest)
//
                        CustomPagerGrap(list)
                    }
                    }
                } else {
                    CircularProgressIndicator()
                }


            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomPagerGrap(list: List<Transactions>) {
    val pages = listOf(
        GraphPagelist.RangeOfExpense,
        GraphPagelist.RangeOfIncome
    )
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        HorizontalPager(
            count = 2, modifier = Modifier
                .fillMaxWidth()
                .weight(10f),
            verticalAlignment = Alignment.Top,
            state = pagerState
        ) {

            CustomGraphCardLayout(pages[currentPage], list[currentPage])


        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(CenterHorizontally)

                .weight(1f),
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = MaterialTheme.colorScheme.inverseSurface
        )
    }

}

@Composable
fun CustomGraphCardLayout(
    graphPagelist: GraphPagelist,
    t: Transactions,
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
        ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = graphPagelist.Hightitle,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = Lato_Bold,
            fontSize = 21.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(20.dp))
        CardHome(detail = t)
    }

}


@Composable
fun PercentageText(percentage: Float) {

    Text(
        text = "The Analysis say's that you have used $percentage % of your income",
        fontFamily = Inter_SemiBold,
        fontSize = 15.sp,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
    )
}


/*
 val
//                    val mapI :Map<String,Transactions> = mapOf(
//                        Pair("highIncome",highinc[0]),
//                        Pair("lowIncome",lowinc[0])
////                                highIncome
//                    )
//                    val mapE :Map<String,Transactions> = mapOf(
//                        Pair("highExpense",highexp[0]),
//                        Pair("lowExpense",lowexp[0])
//                    )
//                    val list= listOf(
//                        mapI.get("highIncome"),
//                        mapI.get("lowIncome"),
//                        mapE.get("highExpense"),
//                        mapE.get("lowExpense"),
//                    )
//                    val listTe= listOf(
//                        "HighI"
//                    ,"HighE",
//                        "LowI",
//                        "Lowe",
//                        ""
//                    )

//                    Toast.makeText(LocalContext.current, "${mapI.get("highIncome")!!.amount}", Toast.LENGTH_SHORT).show()
//                    CustomPagerGraph(list)
 */