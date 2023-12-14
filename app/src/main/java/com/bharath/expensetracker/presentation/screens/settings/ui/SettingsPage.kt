package com.bharath.expensetracker.presentation.screens.settings.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.common.Cons
import com.bharath.expensetracker.presentation.screens.settings.components.ColorListItem
import com.bharath.expensetracker.presentation.screens.settings.viewmodel.SettingsVm
import com.bharath.expensetracker.ui.theme.Allura
import com.bharath.expensetracker.ui.theme.Inter_SemiBold
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SettingsPage(
    settingsVm: SettingsVm = hiltViewModel(),
) {
    val txtColor = if (isSystemInDarkTheme()) {
        Color(0xCDF0EEEE)
    } else Color(0xD4131212)

    var showAlertBox by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    Surface(color = MaterialTheme.colorScheme.surface) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(2f)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    )
            ) {

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "Settings",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 95.sp,
                        fontFamily = Allura
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(6f)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 75.dp)

            ) {
                var checked by remember { mutableStateOf(settingsVm.amoledTheme.value) }
                var checked2 by remember { mutableStateOf(settingsVm.monthlyCalendar.value) }

                var disableColor by remember { mutableStateOf(settingsVm.colorBlocks.value) }
                var scrollingOptions by remember { mutableStateOf(settingsVm.pagerOption.value) }

                Column {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,

                        ) {
                        Text(
                            text = buildAnnotatedString {

                                append("Amoled")
                                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                    append(" Theme")
                                }
                            },
                            fontSize = 22.sp,
                            fontFamily = Inter_SemiBold, color = txtColor
                        )
                        Switch(
                            checked = checked,
                            onCheckedChange = {
                                settingsVm.saveAmoledStatus(it)
                                checked = !checked
                            },
                            colors = SwitchDefaults.colors()
                        )
                    }
                    Text(
                        text = buildAnnotatedString {

                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                append("Note : ")
                            }
                            append("If you turn on dynamic color doesn't applies to the app")

                        },
                        modifier = Modifier.padding(start = 25.dp, end = 25.dp, bottom = 15.dp),
                        fontSize = 12.sp
                    )

                }
                Divider()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {
                    Text(
                        text = buildAnnotatedString {
                            append("Monthly Style")
                            append("\n")
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                append("Calendar")
                            }
                        },
                        fontSize = 22.sp, fontFamily = Inter_SemiBold, color = txtColor
                    )
                    Switch(
                        checked = checked2,
                        onCheckedChange = {

                            settingsVm.saveCalendarState(it)
                            checked2 = !checked2

                        },
                        colors = SwitchDefaults.colors()
                    )
                }
                Divider()
                Column(
                    modifier = Modifier.padding(
                        start = 25.dp,
                        end = 25.dp,
                        top = 15.dp,
                        bottom = 15.dp
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {


                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 22.sp,
                                        fontFamily = Inter_SemiBold,
                                        color = txtColor
                                    )
                                ) {
                                    append("Color")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 22.sp,
                                        fontFamily = Inter_SemiBold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                ) {
                                    append("Scheme")
                                }

                                append(" : Experimental")

                            },
                            fontSize = 15.sp,

                            color = txtColor

                        )

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = buildAnnotatedString {

                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                append("Note : ")
                            }
                            append("Only Works in Amoled Mode")

                        }, fontSize = 12.sp, maxLines = 1, modifier = Modifier.basicMarquee()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    AnimatedVisibility(visible = checked) {


                        LazyRow {

                            itemsIndexed(Cons.ColorSchemes) { ind, it ->
                                ColorListItem(color = it.primaryColor) {
                                    settingsVm.saveCustomColor(ind)
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                            }

                        }
                    }


                }
                Divider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {
                    Text(
                        text = buildAnnotatedString {
                            append("Disable")
                            append("\n")
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                append("Color")
                            }
                            append(" Blocks")
                        },
                        fontSize = 22.sp,
                        fontFamily = Inter_SemiBold, color = txtColor
                    )
                    Switch(
                        checked = disableColor,
                        onCheckedChange = {
                            settingsVm.saveColorBlockState(it)
                            disableColor = !disableColor
                        },
                        colors = SwitchDefaults.colors()
                    )
                }
                Divider()
                Column(
                    modifier = Modifier.padding(
                        start = 25.dp,
                        end = 25.dp,
                        top = 15.dp,
                        bottom = 15.dp
                    )
                ) {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                    ) {
//                        Text(
//                            text = buildAnnotatedString {
//                                append("Scrolling ")
//                                withStyle(
//                                    style = SpanStyle(color = MaterialTheme.colorScheme.primary)
//                                ) {
//                                    append(" Tabs")
//                                }
//                            },
//                            fontSize = 22.sp,
//                            fontFamily = Inter_SemiBold,
//                            color = txtColor
//                        )
//                        Switch(
//                            checked = scrollingOptions,
//                            onCheckedChange = {
//                                settingsVm.savePagerOption(it)
//                                scope.launch {
//
//                                scrollingOptions = !scrollingOptions
//                                }
//
//                            },
//                            colors = SwitchDefaults.colors()
//                        )
//
//                    }

                }
//                Divider()
                Column(
                    modifier = Modifier.padding(
                        start = 25.dp,
                        end = 25.dp,
                        top = 15.dp,
                        bottom = 15.dp
                    )
                ) {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,

                        ) {
                        Text(
                            text = buildAnnotatedString {
                                append("Clear all ")
                                withStyle(
                                    style = SpanStyle(
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                ) {
                                    append("data")
                                }
                            },
                            fontSize = 22.sp,
                            fontFamily = Inter_SemiBold,
                            color = txtColor
                        )
                        IconButton(onClick = {
                            showAlertBox = !showAlertBox

                        }) {
                            Icon(
                                imageVector = Icons.Filled.DeleteForever,
                                contentDescription = "",
                                tint = Color(
                                    0xE0E43C5A
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append("Note : ")
                        }
                        append("Deleted Data can't be Recovered")
                    }, fontSize = 12.sp)
                }
            }

        }

        AnimatedVisibility(visible = showAlertBox) {

            var show by remember { mutableStateOf(false) }
            LaunchedEffect(key1 = true) {
                delay(5000)
                show = true
            }
            AlertDialog(onDismissRequest = {
                showAlertBox = !showAlertBox
            }, confirmButton = {

                AnimatedVisibility(visible = show) {


                    TextButton(onClick = {
                        settingsVm.dropAllData()
                        showAlertBox = !showAlertBox
                    }) {
                        Text(text = "Confirm ! ", color = Color.Red)
                    }
                }
            },

                title = {
                    Text(
                        text = "Are You Sure  ? ",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                text = {


                    Text(
                        text = "Once The Data Is Deleted " + "\n" +
                                "It Can't Be Recovered Back !" + "\n" +
                                "After 5 Seconds Confirm Button Will be Shown",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 15.sp,
                        fontFamily = Inter_SemiBold,
                        color = txtColor
                    )


                },
                shape = RoundedCornerShape(15.dp)
            )
        }

    }
}


@Preview
@Composable
fun SettingsPagePrev() {
    SettingsPage()
}