package com.bharath.expensetracker.presentation.screens.graphscreen.ui.screens

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bharath.expensetracker.presentation.screens.graphscreen.ui.components.HighestOfTransactions
import com.bharath.expensetracker.presentation.screens.graphscreen.ui.components.PercentageText
import com.bharath.expensetracker.presentation.screens.graphscreen.ui.components.PieChart
import com.bharath.expensetracker.presentation.screens.graphscreen.viewmodel.GraphViewModel
import com.bharath.expensetracker.ui.theme.Inter_Bold

@Composable
fun GraphScreen1(
    graphViewModel: GraphViewModel
) {
    Surface(color = MaterialTheme.colorScheme.surface) {



    var income by remember {
        mutableStateOf(0f)
    }
    var expense by remember {
        mutableStateOf(0f)
    }
    var balance by remember {
        mutableStateOf(0f)
    }



    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), contentAlignment = Alignment.Center
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
            modifier = Modifier.fillMaxWidth()
            , contentAlignment = Alignment.Center
        ) {
            if (graphViewModel.isLoadingG.value) {


                val incomeFlow = graphViewModel.incomeSum.collectAsState(initial = 0f)
                val expenseFlow = graphViewModel.expenseSum.collectAsState(initial = 0f)
                income = incomeFlow.value
//                    Toast.makeText(LocalContext.current, income.toString(), Toast.LENGTH_SHORT).show()
                expense = expenseFlow.value



                if (income > 0 && expense > 0) {
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
                .wrapContentHeight(), contentAlignment = Alignment.Center
        ) {
            val percentage = (expense / income) * 100
            androidx.compose.animation.AnimatedVisibility(
                visible = !percentage.isNaN(),
                enter = fadeIn(tween(delayMillis = 800)),
                exit = fadeOut()
            ) {
                PercentageText(percentage = percentage)

            }


        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 70.dp), contentAlignment = Alignment.Center
        ) {

            var visibility by remember {
                mutableStateOf(false)
            }
                visibility =
                graphViewModel.isLoadedRangeHigh.value && graphViewModel.isLoadedRangeLow.value

                if (
                    graphViewModel.lowestTransaction.isNotEmpty()
                    && graphViewModel.highestTransactionExp.isNotEmpty()
                ) {
                    val highest = graphViewModel.getHighestTransaction("")[0]
                    val lowest = graphViewModel.getLowestTransaction("Income")[0]


                    val list = listOf(
                        highest,
                        lowest
                    )
//

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
//                   CardHome(detail = highest)
//                      CardHome(detail = lowest)
//
                        HighestOfTransactions(list)
                    }
                }



        }
    }
}
}