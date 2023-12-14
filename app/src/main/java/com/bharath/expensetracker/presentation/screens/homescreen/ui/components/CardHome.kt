package com.bharath.expensetracker.presentation.screens.homescreen.ui.components


import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.presentation.screens.allTransactionsScreen.ui.components.getNumber
import com.bharath.expensetracker.ui.theme.Inter_Bold
import com.bharath.expensetracker.ui.theme.Lato_Regular
import com.bharath.expensetracker.ui.theme.Money1exp
import com.bharath.expensetracker.ui.theme.Money1inc
import com.bharath.expensetracker.ui.theme.Money2exp
import com.bharath.expensetracker.ui.theme.Money2inc

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardHome(
    detail: Transactions,
    modifier: Modifier,
    showColorBlock :Boolean,
    colorOfCategory : Color
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
