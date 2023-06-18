package com.bharath.expensetracker.screens.homescreen.ui.components

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.common.Cons
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.screens.allTransactionsScreen.ui.components.getNumber
import com.bharath.expensetracker.screens.homescreen.util.HomePageLists
import com.bharath.expensetracker.screens.settings.viewmodel.SettingsVm
import com.bharath.expensetracker.screens.viewmodel.HomeViewModel
import com.bharath.expensetracker.ui.theme.Inter_Bold
import com.bharath.expensetracker.ui.theme.Lato_Regular
import com.bharath.expensetracker.ui.theme.Money1exp
import com.bharath.expensetracker.ui.theme.Money1inc
import com.bharath.expensetracker.ui.theme.Money2exp
import com.bharath.expensetracker.ui.theme.Money2inc
import com.bharath.expensetracker.uielements.NothingHere
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CustomListScreen(
    homePageLists: HomePageLists,
    homeViewModel: HomeViewModel = hiltViewModel(),

) {
    val settingsVm:SettingsVm = hiltViewModel()
    var colorblock by remember{ mutableStateOf(! settingsVm.colorBlocks.value) }
    val list = homeViewModel.getCustomTransaction(homePageLists.keyWord)
        .collectAsState(initial = emptyList())

    if (list.value.isEmpty()) {
        NothingHere()
    } else {

//    val modifiedlist=list.value.subList(0,6)
        Column(modifier = Modifier.fillMaxSize()) {

            Text(
                text = homePageLists.title,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

            AnimatedContent(targetState = list.value) { l ->


                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(l) { detail ->
                        val animatedProgress =
                            remember { androidx.compose.animation.core.Animatable(initialValue = 0.65f) }
                        LaunchedEffect(Unit) {
                            animatedProgress.animateTo(
                                targetValue = 1f,
                                animationSpec = tween(200, easing = LinearEasing)
                            )
                        }
                        val modifier = Modifier.graphicsLayer(
                            scaleY = animatedProgress.value, scaleX = animatedProgress.value
                        )
                        CardHome(detail,
                            modifier = modifier

                            ,showColorBlock =colorblock,
                            Cons.colorMap[detail.category]!!
                            )

                    }
                }

            }
        }
    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardHome(
    detail: Transactions,
    modifier: Modifier,
    showColorBlock :Boolean,
    colorOfCategory :Color
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(10.dp), backgroundColor = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(10.dp)

    ) {
        val brush = Brush.linearGradient(
            listOf(
                Money1exp,
                Money2exp
            )
        )
        val brush2 = Brush.linearGradient(
            listOf(
                Money1inc,
                Money2inc
            )
        )
        var amount = ""
        var colorText = brush
        if (detail.type == "Expense") {
            amount = " -${detail.amount}".uppercase()
        } else {
            colorText = brush2
            amount = "+${detail.amount}".uppercase()
        }
        Row(modifier = Modifier.fillMaxSize()) {
            if (showColorBlock) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(6.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize().background(colorOfCategory))
                }
            }
            Column(
                modifier = Modifier.weight(1.7f), verticalArrangement = Arrangement.Center
            ) {


                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, top = 5.dp)
                        .alpha(0.7f),

                    text = detail.descriptionOfPayment,

                    fontFamily = Inter_Bold,

                    color = MaterialTheme.colorScheme.inverseSurface,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, bottom = 5.dp)
                        .alpha(0.7f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = detail.category, fontFamily = Lato_Regular,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.inverseSurface

                )
                Log.d("Date", detail.date + detail.time)
            }
            Column(modifier = Modifier.weight(1.3f)) {


                Text(

                    text = "₹ ${getNumber(detail.amount.toString())}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                        .alpha(0.9f)
                        .background(colorText)
                        .basicMarquee(animationMode = MarqueeAnimationMode.WhileFocused),

                    fontFamily = Inter_Bold,

                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Visible
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                        .alpha(0.7f),
                    text = "${detail.date }  ${detail.month}  ${detail.year}",
                    fontFamily = Lato_Regular,

                    fontSize = 12.sp,

                    color = MaterialTheme.colorScheme.inverseSurface,

                )
            }
        }

    }
}

@Preview
@Composable
fun Preview() {
//    CardHome(detail = Transactions("Mobile Recharge",2999f,"Expense","Recharge","10:30","04-06-2023"))
}
