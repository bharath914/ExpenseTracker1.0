package com.bharath.expensetracker.screens.addscreen.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.screens.addscreen.viewmodel.AddToDBViewModel
import com.bharath.expensetracker.screens.settings.viewmodel.SettingsVm
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
import java.time.LocalDate
import java.time.LocalTime

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalFoundationApi::class
)
@Composable
@Preview
fun NewAddScreen() {
    val viewModelT: AddToDBViewModel = hiltViewModel()
    val settingsVm: SettingsVm = hiltViewModel()

    Surface(color = MaterialTheme.colorScheme.surface) {
        val green1 = Color(0xC419CB55)
        val green2 = Color(0x6827CF60)
        val green3 = Color(0x2836D26B)
        var displayBox by remember { mutableStateOf(true) }
        val restart = remember { mutableStateOf(false) }

        var showDetails by remember {
            mutableStateOf(false)
        }
        var type by remember {
            mutableStateOf("")
        }
        var colorbrush by remember {
            mutableStateOf(
                Brush.linearGradient(
                    listOf(
                        green3,
                        green2,
                        green1
                    )

                )
            )
        }
        var nameOfPay by remember {
            mutableStateOf("")
        }
        var amountInInr by remember {
            mutableStateOf("")
        }
        var category by remember {
            mutableStateOf("")
        }
        var date by remember {
            mutableStateOf("${LocalDate.now().dayOfMonth}")
        }
        var month by remember {
            mutableStateOf("${LocalDate.now().month}")
        }
        var year by remember {
            mutableStateOf("${LocalDate.now().year}")
        }
        var time by remember {
            mutableStateOf("${LocalTime.now().hour}" + " : " + "${LocalTime.now().minute}")
        }

        val keyboardController = LocalSoftwareKeyboardController.current

        var dropExposed1 by remember { mutableStateOf(false) }

        var tapToSave by remember {
            mutableStateOf(false)
        }
        var calendarStyleBool by remember {
            mutableStateOf(settingsVm.monthlyCalendar.value)
        }
        var calendarStyle by remember {
            mutableStateOf(CalendarStyle.WEEK)
        }
        if (calendarStyleBool) {
            calendarStyle = CalendarStyle.MONTH
        }


        val calendarState = rememberSheetState()
        val clockState = rememberSheetState()
        val incomeListCategories = listOf(
            "Salary",
            "Business Profits",
            "Shares & Stocks",
            "CashBack's",
            "Gifts",
            "Passive Income"
        )
        val expenseListCategories = listOf(
            "Clothing & Apparel",
            "Food",
            "Rent & Monthly Expenses",
            "Movie's & Other",
            "Vehicle & Accessories",
            "Travel",
            "Utilities & Essentials",
            "Electronics",
            "Recharges &Bill Payments",
            "Furniture & Home Equipment",
            "Other"
        )

        val makeNull by remember {
            mutableStateOf(false)
        }




        if (makeNull) {
            showDetails = false
            displayBox = false
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = displayBox, enter = fadeIn() + expandHorizontally(),
                exit = slideOutVertically(tween(400)) + fadeOut()

            ) {


                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {


                    val red1 = Color(0xFFD82445)
                    val red2 = Color(0x7CED4E6B)
                    val redBrush = Brush.linearGradient(
                        listOf(
                            red1,
                            red2
                        )
                    )
                    val greenBrush = Brush.linearGradient(
                        listOf(
                            green3,
                            green2,
                            green1,
                        )
                    )
                    Column {

                        Text(
                            text = "SELECT  \n \n Income / Expense",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp,
                            fontFamily = Inter_SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(80.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            TextButton(
                                onClick = {
                                    type = "Income"

                                    showDetails = !showDetails
                                    displayBox = !displayBox
                                },

                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 30.dp, end = 30.dp)
                                    .background(
                                        brush = greenBrush,
                                        shape = RoundedCornerShape(20)

                                    ),
                            ) {
                                Text(
                                    text = "Income",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    maxLines = 1,
                                    modifier = Modifier.basicMarquee()
                                )

                            }
                            TextButton(
                                onClick = {
                                    type = "Expense"
                                    colorbrush = redBrush
                                    showDetails = !showDetails
                                    displayBox = !displayBox
                                },
                                shape = RoundedCornerShape(40),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 30.dp, end = 30.dp)
                                    .background(

                                        brush = redBrush,
                                        shape = RoundedCornerShape(20)

                                    )
                            ) {
                                Text(
                                    text = "Expense",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    maxLines = 1,
                                    modifier = Modifier.basicMarquee()
                                )

                            }

                        }
                    }
                }

            }


        }
        AnimatedVisibility(
            visible = showDetails,

            ) {
            var typeSpentOrExp = "Spent"
            if (type == "Income") {
                typeSpentOrExp = "Earned"
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                Row(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    horizontalArrangement = Arrangement.Start
                ) {


                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp)
                    ) {
                        Text(
                            text = type,
                            modifier = Modifier
                                .background(
                                    brush = colorbrush,
                                    shape = RoundedCornerShape(30)
                                )
                                .padding(8.dp),
                            fontSize = 22.sp,

                            )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                OutlinedTextField(value = nameOfPay, onValueChange = {
                    nameOfPay = it
                },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(),


                    label = {
                        Text(
                            text = "How do you $typeSpentOrExp it",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                )
                Spacer(modifier = Modifier.height(30.dp))
                OutlinedTextField(
                    value = amountInInr,
                    onValueChange = {
                        amountInInr = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                    }),
                    label = {
                        Text(
                            text = "Enter Amount in $typeSpentOrExp Inr",
                            color = MaterialTheme.colorScheme.primary
                        )
                    },

                    )
                Spacer(modifier = Modifier.height(30.dp))


                ExposedDropdownMenuBox(
                    expanded = dropExposed1, onExpandedChange = {
                        dropExposed1 = !dropExposed1
                    }
                ) {

                    TextField(
                        value = category,
                        onValueChange = {
                            category = it
                        },
                        placeholder = {
                            Text(
                                text = "Select any Category",
                                color = MaterialTheme.colorScheme.primary
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dropExposed1) },
                        readOnly = true,
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(expanded = dropExposed1,
                        onDismissRequest = {
                            dropExposed1 = !dropExposed1
                        }

                    ) {
                        if (type == "Expense") {

                            expenseListCategories.forEachIndexed { index, it ->
                                DropdownMenuItem(text = {
                                    androidx.compose.material.Text(
                                        text = it,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }, onClick = {
                                    category = it
                                    dropExposed1 = !dropExposed1
                                })
                            }

                        } else if (type == "Income") {
                            incomeListCategories.forEach {
                                DropdownMenuItem(text = {
                                    androidx.compose.material.Text(
                                        text = it,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }, onClick = {
                                    category = it
                                    dropExposed1 = !dropExposed1
                                })
                            }

                        }
                    }

                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(modifier = Modifier.fillMaxWidth(0.8f)) {


                    Row(modifier = Modifier.fillMaxWidth(0.5f)) {

                        Column {


                            Text(
                                text = "Date : ${date}",
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(start = 15.dp),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Time : \n$time",
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(start = 15.dp),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                    OutlinedButton(
                        onClick = {
                            calendarState.show()
                        }, colors = ButtonDefaults.outlinedButtonColors(),
                        modifier = Modifier.align(CenterVertically)
                    ) {
                        Text(text = "Change Time", modifier = Modifier.basicMarquee(), maxLines = 1)
                    }

                }
                Spacer(modifier = Modifier.height(30.dp))

                var txt by remember {
                    mutableStateOf("")
                }
                txt = if (tapToSave) "Done" else "Tap to Save"

                var notnull by remember {
                    mutableStateOf(true)
                }


                notnull = amountInInr != "" && nameOfPay != "" && category != ""

                AnimatedVisibility(visible = notnull) {

                    val coroutine = rememberCoroutineScope()

                    OutlinedButton(
                        onClick = {

                            tapToSave = true
                            val transactionDetail = Transactions(
                                descriptionOfPayment = nameOfPay,
                                amount = amountInInr.toFloatOrNull()!!,
                                type = type,
                                category = category,
                                time = time,
                                date = date,
                                month = month,
                                year = year
                            )
                            viewModelT.saveToDb(transactionDetail)
                            coroutine.launch {
                                delay(800)
                                restart.value = true
                            }


                        }, modifier = Modifier.animateContentSize(
                            animationSpec = tween(
                                500,
                                easing = LinearOutSlowInEasing
                            )

                        )
                    ) {
                        Text(text = txt)

                    }
                }
            }
            AnimatedVisibility(visible = restart.value) {
                NewAddScreen()
            }















            CalendarDialog(
                state = calendarState,
                config = CalendarConfig(
//                            CalendarStyle.MONTH,
                    calendarStyle,
                    monthSelection = false,
                ),
                selection = CalendarSelection.Date { dat ->
                    val dates = "${dat.dayOfMonth}"
                    date = dates
                    month = "${dat.month}"
                    year = "${dat.year}"

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

        }
    }

}
