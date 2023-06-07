package com.bharath.expensetracker.screens.homescreen.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.screens.allTransactionsScreen.ui.components.getNumber
import com.bharath.expensetracker.screens.homescreen.util.HomePageLists
import com.bharath.expensetracker.screens.viewmodel.HomeViewModel
import com.bharath.expensetracker.ui.theme.Inter_Bold
import com.bharath.expensetracker.ui.theme.Lato_LightItalic
import com.bharath.expensetracker.ui.theme.Lato_Regular
import com.bharath.expensetracker.ui.theme.Money1exp
import com.bharath.expensetracker.ui.theme.Money1inc
import com.bharath.expensetracker.ui.theme.Money2exp
import com.bharath.expensetracker.ui.theme.Money2inc
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomPagerHome() {

    val pages = arrayOf(
        HomePageLists.RecentExpenses,
        HomePageLists.RecentIncome
    )
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = CenterHorizontally
    ) {

        val nam = remember { mutableStateOf("") }


        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(CenterHorizontally)
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

            CustomListScreen(homePageLists = pages[currentPage])
        }


    }

}

@Composable
fun CustomListScreen(
    homePageLists: HomePageLists,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val list = homeViewModel.getCustomTransaction(homePageLists.keyWord)
        .collectAsState(initial = emptyList())

//    val modifiedlist=list.value.subList(0,6)
    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = homePageLists.title,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(list.value) { detail ->
                CardHome(detail)

            }
        }
    }

}


@Composable
 fun CardHome(detail: Transactions) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(10.dp)
    , backgroundColor = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(10.dp)

    ) {
        val brush =Brush.linearGradient(
            listOf(
                Money1exp,
                Money2exp
            )
        )
        val brush2 =Brush.linearGradient(
            listOf(
                Money1inc,
                Money2inc
            )
        )
        var amount = ""
        var colorText=brush
        if (detail.type == "Expense") {
            amount=" -${detail.amount}".uppercase()
        } else {
            colorText=brush2
           amount= "+${detail.amount}".uppercase()
        }
        Row(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.weight(1.7f)
        , verticalArrangement = Arrangement.Center
            ) {
                


            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 10.dp, top = 5.dp)
                    .alpha(0.7f)

                ,

                text = detail.descriptionOfPayment,

                fontFamily = Inter_Bold,

                color = MaterialTheme.colorScheme.inverseSurface
                , fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 10.dp, bottom = 5.dp)
                        .alpha(0.7f)
                    ,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = detail.category
                    ,  fontFamily = Lato_Regular,
                    fontSize = 13.sp,
                    color=MaterialTheme.colorScheme.inverseSurface

                )
                Log.d("Date",detail.date+detail.time)
            }
            Column(modifier = Modifier.weight(1.3f)) {
                

            Text(

                text = "₹ ${getNumber(detail.amount.toString())}",
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 5.dp)
                    .alpha(0.9f)
                    .background(colorText)

                ,

                fontFamily = Inter_Bold,

                 fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Visible
            )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 5.dp)
                        .alpha(0.8f)
                    ,
                    text = "${detail.date}  ${detail.time}"
                    ,  fontFamily = Lato_LightItalic,
                    fontSize = 13.sp,
                    color=MaterialTheme.colorScheme.inverseSurface

                )
            }
        }

    }
}

@Preview
@Composable
fun Preview() {
    CardHome(detail = Transactions("Mobile Recharge",2999f,"Expense","Recharge","10:30","04-06-2023"))
}
