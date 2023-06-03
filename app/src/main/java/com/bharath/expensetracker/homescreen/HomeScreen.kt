package com.bharath.expensetracker.homescreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bharath.expensetracker.ui.theme.Grad1
import com.bharath.expensetracker.ui.theme.Grad2

@Composable
fun HomeScreen() {
    Surface(color = MaterialTheme.colorScheme.background) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(4f)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                           MaterialTheme.colorScheme.onPrimary
                        )
                    )

                )
                .alpha(0.5f)
            ,
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {


                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(text = "CURRENT BALANCE")
                    Text(text = "5,00,000", textAlign = TextAlign.Center, fontSize = 40.sp)


                }
                Spacer(modifier = Modifier.height(40.dp))
                Box {
                    Column {
                        Row {
                            Text(text = "INCOME", modifier = Modifier.fillMaxWidth(0.5f))
                            Text(text = "EXPENSE", modifier = Modifier.fillMaxWidth(0.5f))
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        IncomeExpenseTxt(income = 9000000f, Expense = 80000f)
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f)
        ) {




        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

//            NavigationBarWithOnlySelectedLabelsSample()


        }

    }
    }
}
@Composable
fun NavigationBarWithOnlySelectedLabelsSample() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Songs", "Artists", "Playlists")

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = item) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index },
                alwaysShowLabel = false
            )
        }
    }
}
@Composable
fun IncomeExpenseTxt(income: Float, Expense: Float) {
    Row {
        Text(text = "$income", fontSize = 20.sp, modifier = Modifier.fillMaxWidth(0.5f))
        Text(text = "$Expense", fontSize = 20.sp, modifier = Modifier.fillMaxWidth(0.5f))
    }


}

@Composable
@Preview(showBackground = true)
fun Preview() {
    HomeScreen()
}