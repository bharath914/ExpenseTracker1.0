package com.bharath.expensetracker.screens.graphscreen.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.screens.graphscreen.util.GraphPagelist
import com.bharath.expensetracker.screens.graphscreen.viewmodel.GraphViewModel
import com.bharath.expensetracker.screens.homescreen.ui.components.CardHome
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomPagerGraph() {
    val pages = arrayOf(
        GraphPagelist.RangeOfExpense,
        GraphPagelist.RangeOfIncome
    )
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val nam = remember { mutableStateOf("") }


        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = MaterialTheme.colorScheme.inverseSurface
        )

        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = 2,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) {

            CustomListGraphScreen(graphPagelist = pages[currentPage])
        }


    }
}

@Composable
private fun CustomListGraphScreen(
    graphPagelist: GraphPagelist,
    graphViewModel: GraphViewModel = hiltViewModel(),
   

    ) {
    val transaction =Transactions(
        "",0f,"","","",""
    )
        
    val finalTransaction =graphViewModel.getHighestTransaction(graphPagelist.type).collectAsState(
        initial = 0f
    )
    val transactionDetail = graphViewModel.getHighestTransactionDetail(finalTransaction.value).collectAsState(
        initial = transaction
    )

    Column(modifier = Modifier.fillMaxSize()) {
//        Card(modifier = Modifier.fillMaxWidth()) {
////            Row(Modifier.fillMaxWidth()) {
////                Text(text = "Highest ${graphPagelist.type}")
////                Text(text = "${finalTransaction.value}")
////            }
//        }
        CardHome(detail = transactionDetail.value)
    }

}
