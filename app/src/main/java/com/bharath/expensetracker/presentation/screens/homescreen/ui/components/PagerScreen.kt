package com.bharath.expensetracker.presentation.screens.homescreen.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.common.Cons
import com.bharath.expensetracker.screens.homescreen.util.HomePageLists
import com.bharath.expensetracker.screens.settings.viewmodel.SettingsVm
import com.bharath.expensetracker.screens.viewmodel.HomeViewModel
import com.bharath.expensetracker.presentation.uielements.NothingHere
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


@Preview
@Composable
fun Preview() {
//    CardHome(detail = Transactions("Mobile Recharge",2999f,"Expense","Recharge","10:30","04-06-2023"))
}
