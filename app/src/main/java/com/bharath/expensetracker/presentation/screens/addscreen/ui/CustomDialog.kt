package com.bharath.expensetracker.presentation.screens.addscreen.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.bharath.expensetracker.ui.theme.Inter_Bold
import com.bharath.expensetracker.ui.theme.Inter_Regular



@Composable
fun CustomDialog() {
    var openDialog = remember{ mutableStateOf(true) }
//    openDialog.value =bool
    AnimatedVisibility(
      visible =   openDialog.value
    ){

        AlertDialog(
            onDismissRequest = {
                openDialog.value = !openDialog.value
            },
            title = { Text(text = "Invalid Details", textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.secondary, fontFamily = Inter_Bold, fontSize = 20.sp, modifier = Modifier.fillMaxWidth()) },
            text = { Text(text = "Please Enter All the Details and then tap on the 'tap to save' button",textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.secondary, fontFamily = Inter_Regular, fontSize = 16.sp) },
            dismissButton = {
                TextButton(onClick = { openDialog.value = !openDialog.value}) {

                    Text(text = "Dismiss", color = MaterialTheme.colorScheme.secondary)
                }

            },
            confirmButton = {}

        )





    }
}
