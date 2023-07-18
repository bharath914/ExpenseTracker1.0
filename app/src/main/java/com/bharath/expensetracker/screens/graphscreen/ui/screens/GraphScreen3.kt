package com.bharath.expensetracker.screens.graphscreen.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.screens.graphscreen.viewmodel.GraphViewModel
import com.bharath.expensetracker.ui.theme.Inter_Regular
import com.bharath.expensetracker.ui.theme.Inter_SemiBold


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun GraphScreen3(
    graphViewModel: GraphViewModel = hiltViewModel(),
) {
    val txtColor = if (isSystemInDarkTheme()) {
        Color(0xCDF0EEEE)
    } else Color(0xD4131212)
    graphViewModel.getMontlySumAndMonths("Income")

    val list1Months = graphViewModel.listOfMonths.value
    val list1MonthsSum = graphViewModel.listOfMonthsSum.value
    Surface(color = MaterialTheme.colorScheme.surface) {

        Scaffold(topBar = {
            TopAppBar(backgroundColor = MaterialTheme.colorScheme.surface) {
                TopBar_GS3(txtColor)
            }
        }, content = {
            Column(
                modifier = Modifier.padding(it)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f)
                        .padding(25.dp)
                        .border(
                            1.dp,
                            Color.Black,
                            shape = RoundedCornerShape(15.dp)
                        )
                ) {
                    Column(modifier = Modifier.padding(start = 25.dp, end = 25.dp)) {

                        Box1(txtColor = txtColor,"Monthly")
                        Spacer(modifier = Modifier.height(25.dp))
                       list1Months.forEachIndexed { index, s ->

                           if (index >=1) {
                               CustomRow(Month = s, amount = list1MonthsSum[index].toString())
                           }
                       }

                    }

                }
                Spacer(modifier = Modifier.height(35.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                        .padding(25.dp)
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(15.dp)
                        )
                ) {
                    Column() {
                        
                    Box1(txtColor = txtColor,"Yearly")
                    Spacer(modifier = Modifier.height(25.dp))

                    }
                }
            }
        })


    }

}

@Composable
fun TopBar_GS3(txtColor: Color) {
    Row(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = buildAnnotatedString {

                append("Savings ")
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append("Details")
                }
            }, modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            fontFamily = Inter_SemiBold,
            color = txtColor
        )

    }

}

@Composable
private fun Box1(txtColor: Color,txt:String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    append("$txt ")
                }
                append(" Savings")
            }, fontSize = 26.sp,
            fontFamily = Inter_SemiBold,
            color = txtColor,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}


@Composable
private fun CustomRow(Month :String,amount:String) {
    Row(Modifier.fillMaxWidth()) {
        Text(
            text = Month,
            modifier = Modifier.weight(4f),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontFamily = Inter_Regular
        )
        Text(
            text = "-",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontFamily = Inter_Regular
        )
        Text(
            text = amount,
            modifier = Modifier.weight(4f),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontFamily = Inter_Regular
        )
    }
}