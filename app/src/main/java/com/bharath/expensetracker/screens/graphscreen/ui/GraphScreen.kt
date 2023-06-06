package com.bharath.expensetracker.screens.graphscreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.screens.addscreen.viewmodel.AddToDBViewModel
import com.bharath.expensetracker.screens.graphscreen.ui.components.PieChart


private val PrimaryCategories= listOf(


    "Expenditure",
    "Balance Left"
)
private val PrimaryColors= listOf(
    Color.Blue,
    Color.Magenta
)
@Composable
fun GraphScreen(

) {

    Surface(color = MaterialTheme.colorScheme.background) {


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        PieChart(
            data = mapOf(
                Pair("sample -1",150),
                Pair("sample -2",120),
                Pair("sample -3",110),
                Pair("sample -4",190),
                Pair("sample -5",160)
            )
        )


    }
}
}
