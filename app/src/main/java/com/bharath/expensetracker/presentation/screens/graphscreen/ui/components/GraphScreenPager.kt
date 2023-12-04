package com.bharath.expensetracker.presentation.screens.graphscreen.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.common.Cons
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.presentation.screens.graphscreen.util.GraphPagelist
import com.bharath.expensetracker.presentation.screens.homescreen.ui.components.CardHome
import com.bharath.expensetracker.presentation.screens.settings.viewmodel.SettingsVm
import com.bharath.expensetracker.ui.theme.Inter_Bold
import com.bharath.expensetracker.ui.theme.Inter_SemiBold
import com.bharath.expensetracker.ui.theme.Lato_Bold
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun HighestOfTransactions(list: List<Transactions>) {
    val pages = listOf(
        GraphPagelist.RangeOfExpense,
        GraphPagelist.RangeOfIncome
    )
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        AnimatedContent(targetState = list, label = "") {
            it


            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                CustomGraphCardLayout(graphPagelist = pages[0], t = list[0])
                CustomGraphCardLayout(graphPagelist = pages[1], t = list[1])

            }


        }

    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CustomGraphCardLayout(
    graphPagelist: GraphPagelist,
    t: Transactions,
) {

    val settingsVm: SettingsVm = hiltViewModel()
    var colorBlock by remember { mutableStateOf(!settingsVm.colorBlocks.value) }
    Column(
        Modifier.fillMaxSize(),

        ) {


        Text(
            text = graphPagelist.Hightitle,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = Lato_Bold,
            fontSize = 21.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(20.dp))
        AnimatedContent(targetState = t) {

            CardHome(detail = it, modifier = Modifier, colorBlock, Cons.colorMap[it.category]!!)
        }
    }

}


@Composable
fun PercentageText(percentage: Float) {

    Text(
        text = buildAnnotatedString {

            append("You Have Used : ")
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = Inter_Bold,
                    fontSize = 18.sp
                )
            ) {
                append("%.2f".format(percentage) + " % ")
            }
            append("Of Your Income")

        },
        fontFamily = Inter_SemiBold,
        fontSize = 15.sp,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp), textAlign = TextAlign.Center
    )
}
