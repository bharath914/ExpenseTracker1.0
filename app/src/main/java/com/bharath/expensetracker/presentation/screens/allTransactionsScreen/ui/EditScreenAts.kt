package com.bharath.expensetracker.presentation.screens.allTransactionsScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Maximize
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.common.Cons
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.presentation.screens.addscreen.ui.CustomDialog
import com.bharath.expensetracker.presentation.screens.addscreen.viewmodel.AddToDBViewModel

import com.bharath.expensetracker.ui.theme.Inter_SemiBold
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalTime

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EditScreenAts(t: Transactions,onclick:() ->Unit) {
    Surface(color = MaterialTheme.colorScheme.background) {
        var valueRupees by remember {
            mutableStateOf(t.amount.toString())
        }
        var nameOfPay by remember {
            mutableStateOf(t.descriptionOfPayment)
        }
        var isExpanded by remember {
            mutableStateOf(false)
        }
        var type by remember {
            mutableStateOf(t.type)

        }

        var isExpanded2 by remember {
            mutableStateOf(false)
        }
        var isSelected by remember {
            mutableStateOf(true)
        }
        var date by remember {
            mutableStateOf(t.date)
        }
        var time by remember {
            mutableStateOf(t.time)
        }

        var month by remember{
            mutableStateOf(t.month)
        }
        var year by remember{
            mutableStateOf(t.year)
        }
        val scope = rememberCoroutineScope()

        var category by remember { mutableStateOf(t.category) }
        val calendarState = rememberSheetState()
        val clockState = rememberSheetState()
        val clickToSave = remember {
            mutableStateOf(false)
        }
        val expenseCat = Cons.expenseListCategories
        val incomeCat = Cons.incomeListCategories

        val keyboardController = LocalSoftwareKeyboardController.current

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Column() {

            Spacer(modifier = Modifier.height(15.dp))
            Icon(
                imageVector = Icons.Filled.Maximize,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(48.dp)
            )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = nameOfPay,

                label = {
                    Text(text = nameOfPay, color = MaterialTheme.colorScheme.primary)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(),
                placeholder = {
                    Text(
                        text = "Enter The Detail of Expense/Income ",
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                onValueChange = {
                    nameOfPay = it
                },


                modifier = Modifier
                    .alpha(0.9f)
                    .background(MaterialTheme.colorScheme.background),
                singleLine = true,
                maxLines = 1,


                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }
                )
            )
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = valueRupees,

                label = {
                    Text(text = valueRupees, color = MaterialTheme.colorScheme.primary)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(),
                placeholder = {
                    Text(
                        text = "Enter The Amount ",
                        color = MaterialTheme.colorScheme.primary

                    )

                },
                onValueChange = {
                    valueRupees = it
                },

                modifier = Modifier
                    .alpha(0.9f)
                    .background(MaterialTheme.colorScheme.background),
                singleLine = true,
                maxLines = 1,

                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }
                )
            )
            Spacer(modifier = Modifier.height(30.dp))
            ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {
                isExpanded = it
            }) {
                TextField(
                    value = type,

                    onValueChange = {},
                    readOnly = true,
                    placeholder = {
                        Text(
                            text = "Select Income/Expense",
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    modifier = Modifier.menuAnchor()

                )
                ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = {
                    isExpanded = false
                }) {
                    DropdownMenuItem(text = {
                        Text(
                            text = "Income",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }, onClick = {
                        type = "Income"
                        isExpanded = false
                        isSelected = true
                    })
                    DropdownMenuItem(text = {
                        Text(
                            text = "Expense",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }, onClick = {
                        type = "Expense"
                        isExpanded = false
                        isSelected = true
                    })
                }
            }

            Spacer(modifier = Modifier.height(30.dp))


            ExposedDropdownMenuBox(expanded = isExpanded2, onExpandedChange = {
                isExpanded2 = it
            }) {
                TextField(
                    value = category, onValueChange =
                    { category = it },
                    readOnly = true,
                    placeholder = {
                        Text(
                            text = "Select Category",
                            modifier = Modifier.alpha(0.6f),
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded2)
                    },

                    modifier = Modifier.menuAnchor()

                )
                ExposedDropdownMenu(expanded = isExpanded2, onDismissRequest = {
                    isExpanded2 = !isExpanded2
                }) {
                    if (type == "Expense") {

                        expenseCat.forEachIndexed { index, it ->
                            DropdownMenuItem(text = {
                                Text(
                                    text = it,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }, onClick = {
                                category = it
                                isExpanded2 = !isExpanded2
                            })
                        }

                    } else if (type == "Income") {
                        incomeCat.forEach {
                            DropdownMenuItem(text = {
                                Text(
                                    text = it,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }, onClick = {
                                category = it
                                isExpanded2 = !isExpanded2
                            })
                        }

                    }
                }

            }

            if (isSelected) {
                CalendarDialog(
                    state = calendarState,
                    config = CalendarConfig(
//                            CalendarStyle.MONTH,
                        CalendarStyle.WEEK,
                        monthSelection = false,
                    ),
                    selection = CalendarSelection.Date { dat ->
                        val dates = "${dat.dayOfMonth}"
                        date = dates
                        month ="${dat.month}"
                        year="${dat.year}"

                        clockState.show()

                    })
                ClockDialog(
                    state = clockState,
                    config = ClockConfig(
                        defaultTime = LocalTime.now()
                    ),

                    selection = ClockSelection.HoursMinutes { hours, minutes ->
                        val timeS = "$hours h: $minutes m"
                        time = timeS


                    })
                Spacer(modifier = Modifier.height(35.dp))
                Text(
                    text = "Time : $date $month",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                    modifier = Modifier.alpha(0.8f),
                    fontFamily = Inter_SemiBold
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedButton(onClick = {
                    calendarState.show()
                }) {
                    Text(
                        text = "Change Time",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.alpha(0.3f)
                    )
                }


            }

            Spacer(modifier = Modifier.height(35.dp))
            OutlinedButton(onClick = {

                clickToSave.value = !clickToSave.value
                scope.launch{
                    delay(200)
                    onclick()
                }

            }
            ) {
                Text(text = "Tap to Save", color = MaterialTheme.colorScheme.primary)
            }
        }
        if (clickToSave.value) {
            t.id?.let {
                var cleanF=
                    com.bharath.expensetracker.presentation.screens.addscreen.cleanFloatingNumberString(
                        valueRupees
                    )
                OnclickSaverAts(
                    descriptionOfPayment = nameOfPay,
                    amount = cleanF!!,
                    type = type,
                    category = category,
                    time = time,
                    date = date,
                    idA = it,
                    month = month,
                    year = year
                )
            }

        }
    }


}


@Preview
@Composable
fun Preview() {
    EditScreenAts(
        t = Transactions(
            "Expense 1",
            10000.0f,
            "Expense",
            "Other",
            "04:69",
            "06",
            month = "06",
            year = "2023"
        ),{}
    )

}

@Composable
private fun OnclickSaverAts(
    descriptionOfPayment: String,
    amount: Float,
    type: String,
    category: String,
    time: String,
    date: String,
    idA: Int,
    month:String,
    year:String
) {

    val viewModelT: AddToDBViewModel = hiltViewModel()

    if (descriptionOfPayment != "" && amount != null && type != "" && category != "") {
        val transactionDetail = Transactions(
            descriptionOfPayment = descriptionOfPayment,
            amount = amount,
            type = type,
            category = category,
            time = time,
            date = date,
            id = idA,
            month =month ,
            year = year
        )

        viewModelT.saveToDb(transactionDetail)

    } else {
        CustomDialog()
    }
}
