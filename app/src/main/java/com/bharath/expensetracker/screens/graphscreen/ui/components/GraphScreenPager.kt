package com.bharath.expensetracker.screens.graphscreen.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.screens.graphscreen.util.GraphPagelist
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun CustomPagerGraph(
    list: List<Map<String, Transactions>>,
) {
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


            CustomListGraphScreen(
                graphPagelist = pages[it],
                map = list[it]
            )
        }


    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun CustomListGraphScreen(
    graphPagelist: GraphPagelist,

    map: Map<String, Transactions>,

    ) {
//    val transaction = Transactions(
//        "", 0f, "", "", "", ""
//    )

    val Highest = map[graphPagelist.highkey]


    val Lowest = map[graphPagelist.lowkey]



    Column(modifier = Modifier.fillMaxSize()) {
        GraphCard(
            desc = graphPagelist.Hightitle,
            brush = graphPagelist.brush,
            amount = "₹ ${Highest!!.descriptionOfPayment}"
        )
        Spacer(modifier = Modifier.height(20.dp))
        GraphCard(
            desc = graphPagelist.Lowtitle,
            brush = graphPagelist.brush,
            amount = "₹ ${Lowest!!.descriptionOfPayment}"
        )

    }

}

@Composable
fun GraphCard(
    desc: String,
    brush: Brush,
    amount: String,
) {
    OutlinedCard(
        shape = MaterialTheme.shapes.large, modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = desc, modifier = Modifier.weight(5.5f))
            Text(
                text = amount, modifier = Modifier
                    .weight(4.5f)
                    .background(brush)
            )

        }

    }
}
