package com.bharath.expensetracker.screens.graphscreen

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.screens.addscreen.viewmodel.AddToDBViewModel

@Composable
fun GraphScreen(
    viewModelA: AddToDBViewModel = hiltViewModel()
) {
    Surface(color = MaterialTheme.colorScheme.background) {


    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
      Box(modifier = Modifier.fillMaxSize()){

          val paym=viewModelA.list.collectAsState(initial = emptyList())
          LazyColumn(modifier = Modifier.fillMaxSize()){
              items(paym.value){
                    CustomCard(it)
              }
          }

      }
    }
    }
}
@Composable
fun CustomCard(pay:Transactions){
    Card(modifier = Modifier
        .fillMaxWidth()

        .padding(15.dp)
    , backgroundColor = MaterialTheme.colorScheme.background
    ) {
        Column {
            Text(text = pay.amount, color = MaterialTheme.colorScheme.primary, fontSize = 35.sp)
            Text(text = pay.descriptionOfPayment ?:"Not Available", color = MaterialTheme.colorScheme.primary, fontSize = 20.sp)
        }
        
    }

}