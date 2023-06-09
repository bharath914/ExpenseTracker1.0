package com.bharath.expensetracker.screens.addscreen.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplanemodeActive
import androidx.compose.material.icons.filled.BikeScooter
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.LocalDate
import java.time.LocalTime


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AddScreen(

) {
    Surface(color = MaterialTheme.colorScheme.background) {
        var valueRupees by remember {
            mutableStateOf("")
        }
        var nameOfPay by remember {
            mutableStateOf("")
        }
        var isExpanded by remember {
            mutableStateOf(false)
        }
        var type by remember {
            mutableStateOf("")

        }

        var isExpanded2 by remember {
            mutableStateOf(false)
        }
        var isSelected by remember {
            mutableStateOf(false)
        }
        var date by remember{
            mutableStateOf("${LocalDate.now()}")
        }
        var time by remember {
            mutableStateOf("${LocalTime.now().hour }"+" h: "+"${LocalTime.now().minute}"+" m")
        }

        var category by remember { mutableStateOf("") }
        val calendarState = rememberSheetState()
        val clockState= rememberSheetState()
        val clickToSave = remember {
            mutableStateOf(false)
        }

        val keyboardController = LocalSoftwareKeyboardController.current
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            OutlinedTextField(
                value = nameOfPay,

                label = {
                    Text(text = nameOfPay, color = MaterialTheme.colorScheme.primary)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(),
                placeholder = {
                    Text(text = "Enter The Detail of Expense/Income ", color = MaterialTheme.colorScheme.primary)
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
                ),
            )
            Spacer(modifier = Modifier.height(35.dp))
            OutlinedTextField(
                value =valueRupees,

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
                ),
            )
            Spacer(modifier = Modifier.height(35.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {





                    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {
                        isExpanded = it
                    }) {
                        TextField(
                            value = type,

                            onValueChange = {},
                            readOnly = true,
                            placeholder = {
                                Text(text = "Select Income/Expense", color = MaterialTheme.colorScheme.primary)
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

                    Spacer(modifier = Modifier.height(35.dp))
                    val expenseCat = arrayOf(
                        "Clothing",
                        "Food",
                        "Movie",
                        "Vehicle",
                        "Travel",
                        "Utilities",
                        "Electronics",
                        "Other"
                    )
                    val iconList= listOf(
                        Icons.Default.Fastfood,
                        Icons.Default.Movie,
                        Icons.Default.BikeScooter,
                        Icons.Default.AirplanemodeActive,

                    )
                    val incomeCat = arrayOf("Salary", "Business profit", "Gifts", "CashBack", "Other")
//                    if (isSelected) {
                    AnimatedVisibility(visible = isSelected) {


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

                                    expenseCat.forEachIndexed {index ,it->
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


                    }
//                    if (isSelected) {
                       AnimatedVisibility(visible = isSelected) {

                           Column(
                               modifier = Modifier.fillMaxWidth(),
                               horizontalAlignment = Alignment.CenterHorizontally,
                               verticalArrangement = Arrangement.Center
                           ){
                        CalendarDialog(
                            state = calendarState,
                            config = CalendarConfig(
//                            CalendarStyle.MONTH,
                                CalendarStyle.WEEK,
                                monthSelection = false,
                            ),
                            selection = CalendarSelection.Date { dat ->
                                val dates="$dat"
                                date=dates
                               
                                clockState.show()

                            })
                        ClockDialog(
                            state = clockState,
                            config=ClockConfig(
                                defaultTime = LocalTime.now()
                            ),

                            selection =ClockSelection.HoursMinutes{hours, minutes ->
                                val timeS="$hours h: $minutes m"
                                time=timeS


                        } )
                        Spacer(modifier = Modifier.height(35.dp))
                        Text(text = "Time : $time              $date", color = MaterialTheme.colorScheme.primary,
                        fontSize = 18.sp, modifier = Modifier.alpha(0.8f), fontStyle = FontStyle.Italic)
                        Spacer(modifier = Modifier.height(20.dp))
                        OutlinedButton(onClick = {
                            calendarState.show()
                        }) {
                            Text(text = "Change Time",color = MaterialTheme.colorScheme.primary, modifier = Modifier.alpha(0.3f))
                        }


                    }
                }
                }
            }
            Spacer(modifier = Modifier.height(35.dp))
            OutlinedButton(onClick = {

               clickToSave.value= ! clickToSave.value

            }
            ) {
                Text(text = "Tap to Save", color = MaterialTheme.colorScheme.primary)
            }


        }
        if (clickToSave.value){
            OnclickSaver(
                descriptionOfPayment = nameOfPay,
                amount =valueRupees ,
                type = type,
                category = category,
                time = time,
                date = date
            )
        }
    }


}

