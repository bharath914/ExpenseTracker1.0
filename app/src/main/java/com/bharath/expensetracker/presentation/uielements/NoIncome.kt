package com.bharath.expensetracker.presentation.uielements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bharath.expensetracker.R
import com.bharath.expensetracker.ui.theme.Inter_ExtraLight

@Composable
fun NoIncome() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {

        Column {
            Spacer(modifier = Modifier.height(25.dp))
            Text(
                text = "oops seems like no data available ",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 25.sp,
                fontFamily = Inter_ExtraLight,
            )
            Text(
                text = "Add Income To Show Here",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.inverseSurface,
                fontSize = 20.sp,
                fontFamily = Inter_ExtraLight,
            )

            Image(
                painter = painterResource(id = R.drawable.piggybank),
                contentDescription = "",
                modifier = Modifier.padding(50.dp)
            )
        }


    }


}