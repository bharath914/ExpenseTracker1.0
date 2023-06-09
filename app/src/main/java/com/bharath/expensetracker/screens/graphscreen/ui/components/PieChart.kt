package com.bharath.expensetracker.screens.graphscreen.ui.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bharath.expensetracker.ui.theme.BalanceColorInPie
import com.bharath.expensetracker.ui.theme.ExpenseColorInPie


@Composable
fun PieChart(
    data:Map<String,Int>,
    radiusOuter:Dp =90.dp,
    chartBarWidth:Dp=20.dp,
    animDuration:Int =1000,
) {
    val totalSum =data.values.sum()
    val floatValue = mutableListOf<Float>()
    data.values.forEachIndexed{index, values ->
        floatValue.add(index,360*values.toFloat() / totalSum.toFloat())
    }
    val colors= listOf(
       ExpenseColorInPie,
        BalanceColorInPie

    )
    var animationPlayed by remember{ mutableStateOf(false) }
    var lastValue=0f
    val animateSize by animateFloatAsState(
        targetValue = if (animationPlayed)radiusOuter.value*2f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )

        )

    val animateRotation by animateFloatAsState(
        targetValue = if (animationPlayed)90f*11f else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )
    LaunchedEffect(key1 = true ){
        animationPlayed=true
    }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        Box(
            modifier = Modifier.size(animateSize.dp),
            contentAlignment = Alignment.Center
            ){
            Canvas(modifier = Modifier
                .size(radiusOuter * 2f)
                .rotate(animateRotation)){
                floatValue.forEachIndexed { index, value ->
                    drawArc(
                        color = colors[index],
                        lastValue,
                        value,
                        useCenter = false,
                        style = Stroke(chartBarWidth.toPx(), cap = StrokeCap.Butt)
                    )
                    lastValue +=value
                }
            }

        }
            DetailsPiechart(data,colors)

    }



}

@Composable
fun DetailsPiechart(data: Map<String, Int>, colors: List<Color>) {

    Column(modifier = Modifier
        .padding(top = 60.dp)
        .fillMaxWidth())
    {
        data.values.forEachIndexed { index, value ->
            DetailsPieChartItem(data = Pair(data.keys.elementAt(index),value), color =colors[index] )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }

}

@Composable
fun DetailsPieChartItem(
    data: Pair<String, Int>,
    height:Dp=45.dp,
    color:Color
) {

    Surface(
        modifier = Modifier.padding(vertical = 10.dp, horizontal = 30.dp),
        color = Color.Transparent
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier
                .background(color = color, shape = RoundedCornerShape(10.dp))
                .size(height))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = data.first,
                    modifier = Modifier.padding(start = 15.dp),
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp,
                    color= MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "₹ ${getNumber( data.second.toString())}",
                    modifier = Modifier.padding(start = 15.dp),
                    fontWeight = FontWeight.Medium,
                    fontSize = 22.sp,
                    color= MaterialTheme.colorScheme.primary
                )
            }
        }

    }

}


private fun getNumber(string: String):String {
    var value = string

    var separator = 3
    var visit = 0
    var number = ""
    for (i in value.length - 1 downTo 0) {

        visit += 1
        number += value[i]
        if (visit == separator && i != 0) {
            number += ","
            separator = 2
            visit = 0
        }
    }
    return number.reversed()
}