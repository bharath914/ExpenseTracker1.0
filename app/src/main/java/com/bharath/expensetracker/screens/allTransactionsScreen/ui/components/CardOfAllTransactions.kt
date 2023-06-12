package com.bharath.expensetracker.screens.allTransactionsScreen.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.screens.allTransactionsScreen.ui.SetUpNavGraph
import com.bharath.expensetracker.screens.allTransactionsScreen.viewmodel.ATSViewModel
import com.bharath.expensetracker.ui.theme.Inter_SemiBold
import com.bharath.expensetracker.ui.theme.Lato_Bold
import com.bharath.expensetracker.ui.theme.Lato_Regular
import com.bharath.expensetracker.ui.theme.Money1exp
import com.bharath.expensetracker.ui.theme.Money1inc
import com.bharath.expensetracker.ui.theme.Money2exp
import com.bharath.expensetracker.ui.theme.Money2inc


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AtsCard(
    detail: Transactions,
    atsViewModel: ATSViewModel = hiltViewModel(),
    modifier: Modifier,

    ) {
    var edit by remember {
        mutableStateOf(false)
    }

    val navController = rememberNavController()
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )

    ) {
        var color = Color.Black
        if (isSystemInDarkTheme()) {
            color = Color.White
        }
        val brush = Brush.linearGradient(
            listOf(
                Money2exp,
                Money1exp,
            )
        )
        val brush2 = Brush.linearGradient(
            listOf(
                Money2inc,
                Money1inc,
            )
        )


        var amountColor = brush2
        if (detail.type == "Expense") {

            amountColor = brush
        }
        var expanded by remember {
            mutableStateOf(false)
        }

        Row(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxSize()
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = detail.descriptionOfPayment,
                        maxLines = 1,

                        modifier = Modifier.padding(start = 10.dp, top = 8.dp).basicMarquee(), color = color,
                        fontFamily = Inter_SemiBold,
                        fontSize = 16.sp
                    )
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = detail.category,
                            modifier = Modifier
                                .padding(start = 10.dp, top = 2.dp)
                                .weight(2.5f)
                                .alpha(0.7f)
                                .basicMarquee(animationMode = MarqueeAnimationMode.Immediately),
                            color = color,
                            fontFamily = Lato_Regular,
                            fontSize = 14.sp,
                            maxLines = 1
                        )
                        Text(
                            text = "${detail.date}  ${detail.month} ${detail.year}",
                            modifier = Modifier
                                .weight(3f)
                                .padding(start = 10.dp, top = 5.dp)
                                .align(CenterVertically)
                                .alpha(0.7f),
                            fontFamily = Lato_Regular,
                            color = color,
                            fontSize = 12.sp,
                            maxLines = 1

                        )
                    }

                }
            }
            Box(modifier = Modifier.weight(2f)) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = CenterVertically) {
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(
                            text = "₹${getNumber(detail.amount.toString())}",
                            fontFamily = Lato_Bold,
                            fontSize = 17.sp,
                            modifier = Modifier
                                .weight(4f)
                                .background(brush = amountColor)
                                .basicMarquee(animationMode = MarqueeAnimationMode.Immediately),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            softWrap = true,

                            )
                        IconButton(
                            onClick = { expanded = true }, modifier = Modifier
                                .weight(1f)
                                .padding(end = 5.dp)
                        ) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                        }
                        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                            DropdownMenuItem(text = {
                                Text(
                                    text = "Delete",
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }, onClick = {
                                atsViewModel.deleteTransaction(detail)

                                expanded = false
                            }, trailingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Delete",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            })
                            DropdownMenuItem(text = {
                                Text(
                                    text = "Edit",
                                    color = MaterialTheme.colorScheme.primary
                                )
                            },
                                onClick = {
                                    edit = !edit

                                }, trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.Edit,
                                        contentDescription = "Edit",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                })
                        }

                    }
                }

            }
        }

    }
    AnimatedVisibility(visible = edit) {


        SetUpNavGraph(navController, detail)

        navController.navigate("Edit_Screen")

    }
}


@Preview
@Composable
private fun PreviewFun() {
//   AtsCard(detail = Transactions("Bought Samsung s23Ultra",125000f,"Expense","Electronics","4:30","12-2-2024"),{
//       Unit
//   })
}

fun getNumber(string: String): String {
    var value = string.substring(0, string.indexOf('.'))

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
    return number.reversed() + string.substring(string.indexOf('.'), string.length)
}