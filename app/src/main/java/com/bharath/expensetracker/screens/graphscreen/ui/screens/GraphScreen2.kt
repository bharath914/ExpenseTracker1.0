package com.bharath.expensetracker.screens.graphscreen.ui.screens

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bharath.expensetracker.screens.graphscreen.ui.screens.data.Details
import com.bharath.expensetracker.screens.graphscreen.viewmodel.GraphViewModel
import com.bharath.expensetracker.ui.theme.Inter_Bold

@Composable
fun GraphScreen2(
    graphViewModel: GraphViewModel,
    type: String,
    list: List<Details>,
    totalSum: Float = 0f,


    ) {
    Surface(color = MaterialTheme.colorScheme.surface) {




        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        )
        {

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(bottom = 60.dp)
            ) {
                Text(
                    text = type,
                    fontSize = 35.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.weight(0.55f)) {
                    Stats_(list, totalSum)
                }
                Box(
                    modifier = Modifier
                        .weight(0.45f)
                        .padding(start = 15.dp, end = 15.dp)
                ) {
                    Stats_List(list,graphViewModel)
                }


            }
        }
    }
}

@Composable
fun Stats_List(
    list: List<Details>,
    graphViewModel: GraphViewModel



    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()


    ) {


         LazyColumn {
            items(list) { it ->
                Stats_List_item(details = it, graphViewModel =graphViewModel )
                Spacer(modifier = Modifier.height(8.dp))
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Stats_List_item(
    details: Details,
    graphViewModel: GraphViewModel
) {

    var animationPlayed by remember { mutableStateOf(false) }
    val aniamtionsize by animateDpAsState(
        targetValue = if (animationPlayed) 45.dp else 0.dp,
        animationSpec = tween(500, easing = EaseIn)

    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Column(
        Modifier
            .fillMaxWidth()
            .height(aniamtionsize)

            .background(shape = RoundedCornerShape(25), color = MaterialTheme.colorScheme.surface)

    ) {
        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color = details.color, shape = RoundedCornerShape(30))
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = details.name,
                color = MaterialTheme.colorScheme.inverseSurface,
                modifier = Modifier.weight(3f).basicMarquee(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis

            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "₹ ${graphViewModel.getNumber(details.Value.toString()) }",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(2f)
                    .basicMarquee(),
                fontSize = 18.sp,
                fontFamily = Inter_Bold,
                maxLines = 1
            )

        }

    }
}


@Composable
fun Stats_(
    list: List<Details>,
    totalSum: Float = 0f,

) {
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        var i = 0

        LazyColumn {
            items(list) { item: Details ->
                var fl: Float = item.Value / totalSum
                if (1.0 - fl >= 0.2){
                    fl += 0.1f
                }
                IndividualStat(details = item, width = fl)


            }
        }
    }


}


@Composable
fun IndividualStat(
    details: Details,
    width: Float,

) {
    val animationPlayed = remember {
        mutableStateOf(false)
    }
    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed.value) 1f else 0f,
        tween(500, easing = EaseIn)
    )
    LaunchedEffect(key1 = true) {
        animationPlayed.value = true
    }




        Box(
            modifier = Modifier
                .fillMaxWidth(animateSize)
                .padding(10.dp)
                .height(35.dp)

                .background(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.inverseSurface
                )

        ) {


            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(width)
                    .background(
                        details.color, shape = CircleShape
                    ),
                verticalAlignment = Alignment.CenterVertically,

                ) {


            }
        }

}
