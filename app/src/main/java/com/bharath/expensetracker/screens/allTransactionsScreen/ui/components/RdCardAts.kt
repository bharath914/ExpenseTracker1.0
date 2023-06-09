package com.bharath.expensetracker.screens.allTransactionsScreen.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.screens.allTransactionsScreen.viewmodel.ATSViewModel
import com.bharath.expensetracker.ui.theme.Inter_SemiBold
import com.bharath.expensetracker.ui.theme.Lato_Bold
import com.bharath.expensetracker.ui.theme.Lato_Regular
import com.bharath.expensetracker.ui.theme.Money1exp
import com.bharath.expensetracker.ui.theme.Money1inc
import com.bharath.expensetracker.ui.theme.Money2exp
import com.bharath.expensetracker.ui.theme.Money2inc

@Composable
fun Rd_Card(detail: Transactions,
            atsViewModel: ATSViewModel = hiltViewModel(),

            modifier: Modifier

) {
    var show by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )

    ){
        var color= Color.Black
        if (isSystemInDarkTheme()){
            color= Color.White
        }
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


        var amountColor=brush2
        if (detail.type=="Expense"){

            amountColor=brush
        }
        var expanded by remember{
            mutableStateOf(false)
        }

        Row(modifier = Modifier.fillMaxSize()) {

            Box(modifier = Modifier
                .weight(3f)
                .fillMaxSize()
            ){
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = detail.descriptionOfPayment ,
                        maxLines = 1,

                        modifier = Modifier.padding(start = 10.dp, top = 8.dp)
                        , color =color,
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
                            ,
                            color=color,
                            fontFamily = Lato_Regular,
                            fontSize = 14.sp,
                            maxLines = 1
                        )
                        Text(
                            text = detail.date ,
                            modifier = Modifier
                                .weight(3f)
                                .padding(start = 10.dp, top = 5.dp)
                                .align(Alignment.CenterVertically)
                                .alpha(0.7f),
                            fontFamily =    Lato_Regular,
                            color=color,
                            fontSize = 12.sp,
                            maxLines = 1

                        )
                    }

                }
            }
            Box(modifier = Modifier.weight(2f)){

                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(
                            text ="₹${ getNumber(detail.amount.toString())}",
                            fontFamily = Lato_Bold,
                            fontSize = 17.sp,
                            modifier = Modifier
                                .weight(4f)
                                .background(brush = amountColor)
                            ,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                            , softWrap = true,

                            )
                        IconButton(onClick = {expanded=true  }, modifier = Modifier
                            .weight(1f)
                            .padding(end = 5.dp)) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                        }
                        DropdownMenu(expanded = expanded, onDismissRequest = { expanded=false }) {
                            DropdownMenuItem(text = { Text(text = "Delete",color= MaterialTheme.colorScheme.primary) }, onClick = {
                             show=true

                                expanded=false
                            }, trailingIcon = {
                                Icon(imageVector = Icons.Filled.Delete, contentDescription ="Delete", tint = MaterialTheme.colorScheme.primary )
                            })
                            DropdownMenuItem(text = { Text(text = "Restore",color= MaterialTheme.colorScheme.primary) }, onClick = {
                              atsViewModel.restoreRd(detail)

                                expanded=false
                            }, trailingIcon = {
                                Icon(imageVector = Icons.Filled.Restore, contentDescription ="Restore", tint = MaterialTheme.colorScheme.primary )
                            })

                        }

                    }
                }

            }
        }

    }
    AnimatedVisibility (visible = show){
        AlertDialog(onDismissRequest = {show=false }, confirmButton = {
            TextButton(onClick = {
                atsViewModel.deleteRd(detail)
               show =false

            }){
                Text(text = "Confirm")
            }
        },
            dismissButton = {
                TextButton(onClick = {show =false }) {
                    Text(text = "Dismiss")
                }
            },
            shape = MaterialTheme.shapes.large,
            title = { Text(text = "Delete Permanently")},
            text = { Text(text = "Are you Sure ,Once Deleted It can't be Reverted")}
            , containerColor = MaterialTheme.colorScheme.primaryContainer,
            textContentColor = MaterialTheme.colorScheme.inverseSurface,
            titleContentColor = MaterialTheme.colorScheme.inverseSurface

        )
    }
}
