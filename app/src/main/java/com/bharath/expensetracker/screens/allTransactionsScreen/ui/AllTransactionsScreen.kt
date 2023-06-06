package com.bharath.expensetracker.screens.allTransactionsScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.data.model.Transactions
import com.bharath.expensetracker.screens.allTransactionsScreen.ui.components.AtsCard
import com.bharath.expensetracker.screens.allTransactionsScreen.ui.components.Rd_Card
import com.bharath.expensetracker.screens.allTransactionsScreen.viewmodel.ATSViewModel
import com.bharath.expensetracker.ui.theme.Inter_Bold


@Composable
@Preview
fun AllTransactionsScreen(
  atsViewModel: ATSViewModel = hiltViewModel(),
) {
    Surface(color = MaterialTheme.colorScheme.background) {

        var tabIndex by remember{ mutableStateOf(0) }
        val tabs= listOf(
            "All"
        , "Expenses",
        "Income",
            "Recently Deleted"
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp)) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(MaterialTheme.colorScheme.primaryContainer)) {
                    Row() {


                        Text(
                            text = "History", fontSize = 45.sp,
                            fontFamily = Inter_Bold,
                            letterSpacing = 1.sp,
                            color = MaterialTheme.colorScheme.inversePrimary,
                            modifier = Modifier
                                .padding(10.dp)
                                .weight(6f)


                        )


                    }
                }
            
           ScrollableTabRow(

                selectedTabIndex = tabIndex,
               modifier= Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.primaryContainer,
               edgePadding = 10.dp,

            ) {

                tabs.forEachIndexed {tabindex, tab ->
                    Tab(
                        selected = tabIndex ==tabindex,
                        onClick = {

                                  tabIndex=tabindex
                                  },
                    text = {
                        Text(
                            text = tab,
                            fontSize = 15.sp,
                            fontFamily = Inter_Bold ,
                            modifier = Modifier.alpha(0.7f)
                            )
                           },
                        selectedContentColor = MaterialTheme.colorScheme.primary
            ,  unselectedContentColor = MaterialTheme.colorScheme.inverseSurface
                    )

                }
            }



            when(tabIndex){
                0 -> {
                    CustomList(list =atsViewModel.allPays .collectAsState(initial = emptyList()).value )
                }
                1 ->{
                    CustomList(list = atsViewModel.getAllExpensesAts() .collectAsState(initial = emptyList()).value)

                }
                2->{
                    CustomList(list = atsViewModel.getAllIncomeAts().collectAsState(initial = emptyList()).value)
                }
                3->{
                    RD_CustomList(list = atsViewModel.getAllRd().collectAsState(initial = emptyList()).value)
                }
            }
        
        }
        }


    }
}

@Composable
fun RD_CustomList(list: List<Transactions>){
    LazyColumn {
        items(list){
            Rd_Card(detail = it)

        }

    }
}

@Composable
fun CustomList(list: List<Transactions>) {

    LazyColumn {
        items(list){
            AtsCard(detail = it)

        }

    }
}
