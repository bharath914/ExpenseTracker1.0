package com.bharath.expensetracker.homescreen


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.expensetracker.ui.theme.Grad1
import com.bharath.expensetracker.ui.theme.Grad2
import com.bharath.expensetracker.ui.theme.list.CustomCard
import com.bharath.expensetracker.ui.theme.viewmodel.NameViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val viewModel:NameViewModel= hiltViewModel()
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
                            Grad1, Grad2
                        )
                    )
                ),
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
                .weight(6f)
        ) {



            Toast.makeText(LocalContext.current, viewModel.GetName(), Toast.LENGTH_SHORT).show()
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