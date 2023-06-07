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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.screens.graphscreen.ui.components.PieChart
import com.bharath.expensetracker.screens.graphscreen.viewmodel.GraphViewModel
import com.bharath.expensetracker.ui.theme.Inter_Bold
import com.bharath.expensetracker.ui.theme.Inter_SemiBold
import com.google.accompanist.pager.ExperimentalPagerApi


@OptIn(ExperimentalPagerApi::class)
@Composable
fun GraphScreen(
    graphViewModel: GraphViewModel = hiltViewModel()
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
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                , contentAlignment = Alignment.Center){
                Text(text = "Expense Vs Income",color=MaterialTheme.colorScheme.primary, modifier = Modifier.alpha(0.8f) , fontFamily = Inter_Bold, fontSize = 25.sp)
            }
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), contentAlignment = Alignment.Center
            ) {
                if ( graphViewModel.isLoadingG.value) {


                    val incomeFlow = graphViewModel.incomeSum.collectAsState(initial = 0f)
                    val expenseFlow = graphViewModel.expenseSum.collectAsState(initial = 0f)
                    income = incomeFlow.value
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
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), contentAlignment = Alignment.Center){
                val percentage= (expense /income)*100
                if (!percentage.isNaN()){
                PercentageText(percentage = percentage)

            }






        }

            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()){
//                CustomPagerGraph()
            }
        }
        }
    }


@Composable
fun PercentageText(percentage:Float) {

    Text(
        text = "The Analysis say's that you have used $percentage % of your income",
        fontFamily = Inter_SemiBold,
        fontSize = 15.sp,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
    )
}


