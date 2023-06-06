package com.bharath.expensetracker.screens.homescreen.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.screens.homescreen.ui.components.CustomPagerHome
import com.bharath.expensetracker.screens.viewmodel.HomeViewModel
import com.bharath.expensetracker.ui.theme.Inter_Bold
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    Surface(color = MaterialTheme.colorScheme.background) {

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope= rememberCoroutineScope()
        val list= listOf("Profile","Settings","FeedBack","Rate us on PlayStore")

        val selectedItem= remember {
            mutableStateOf(list[0])
        }
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(modifier = Modifier.height(15.dp))
                    list.forEach {item->
                        NavigationDrawerItem(
                            label = { Text(text = item) },
                            selected = item == selectedItem.value,
                            onClick = { scope.launch { drawerState.close() }
                                selectedItem.value=item
                            },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )

                    }
                }
            }
        ){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val sumOfExpenses=homeViewModel.expSumArr!!.collectAsState(initial = 0.0f)
        val sumOfIncomes=homeViewModel.incomeSumArr!!.collectAsState(initial = 0.0f)
        val BalanceAmount= remember {
            mutableStateOf(0.0f)
        }


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
              
            ,
//            contentAlignment = Alignment.Center
        ) {
            if (homeViewModel.checkExpisEmpty().collectAsState(initial = 0).value !=0 && homeViewModel.checkIncisEmpty().collectAsState(initial = 0).value !=0){
            BalanceAmount.value=sumOfIncomes.value - sumOfExpenses.value
            }
            IconButton(onClick = {
                scope.launch { drawerState.open()}


            },
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 5.dp)
                    .scale(1.4f)
            ){
                Icon(imageVector=Icons.Default.Menu , contentDescription ="Open Menu" )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(40.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(text = "CURRENT BALANCE")
                    Text(text = "${BalanceAmount.value}", textAlign = TextAlign.Center, fontSize = 40.sp)


                }
                Spacer(modifier = Modifier.height(40.dp))
                Box {
                    Column {
                        Row {
                            Text(text = "INCOME", modifier = Modifier.fillMaxWidth(0.5f))
                            Text(text = "EXPENSE", modifier = Modifier.fillMaxWidth(0.5f))
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        if (homeViewModel.checkExpisEmpty().collectAsState(initial = 0).value !=0 && homeViewModel.checkIncisEmpty().collectAsState(initial = 0).value !=0) {

                            IncomeExpenseTxt(
                                income =
                                sumOfIncomes.value,
                                Expense =
                                sumOfExpenses.value
                            )
                        }
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f)
        ) {
            




            CustomPagerHome()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {


        }

    }
    }
    }
}

@Composable
fun IncomeExpenseTxt(income: Float, Expense: Float) {
    Row {
        Text(text = "$income", fontSize = 18.sp, modifier = Modifier
            .fillMaxWidth(0.5f)
            .alpha(0.8f),
            maxLines = 1, fontFamily = Inter_Bold
        )
        Text(text = "$Expense", fontSize = 18.sp, modifier = Modifier
            .fillMaxWidth(0.5f)
            .alpha(0.8f)
            , maxLines = 1, fontFamily = Inter_Bold
        )
    }


}

@Composable
@Preview(showBackground = true)
fun Preview() {
    HomeScreen()
}
