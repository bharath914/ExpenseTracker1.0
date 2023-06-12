package com.bharath.expensetracker.screens.homescreen.ui



import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.expensetracker.screens.allTransactionsScreen.ui.components.getNumber
import com.bharath.expensetracker.screens.graphscreen.ui.components.PercentageText
import com.bharath.expensetracker.screens.homescreen.ui.components.CustomPagerHome
import com.bharath.expensetracker.screens.viewmodel.HomeViewModel
import com.bharath.expensetracker.ui.theme.Inter_Bold
import com.bharath.expensetracker.ui.theme.Inter_SemiBold
import com.bharath.expensetracker.ui.theme.Lato_Regular
import com.bharath.expensetracker.ui.theme.Money1exp
import com.bharath.expensetracker.ui.theme.Money1inc
import com.bharath.expensetracker.ui.theme.Money2exp
import com.bharath.expensetracker.ui.theme.Money2inc
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    Surface(color = MaterialTheme.colorScheme.surface) {

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val currentMonth = "${LocalDate.now().month}"
        val list = listOf( "FeedBack", "Rate us on PlayStore")
        var gotoSelected by remember{ mutableStateOf(false) }
        val selectedItem = remember {
            mutableStateOf("")

        }

        if (gotoSelected){
        when(selectedItem.value){
             "FeedBack" ->{
                sendEmail(LocalContext.current)
                gotoSelected =false
            }
            "Rate us on PlayStore" ->{

            }
        }
        }

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(modifier = Modifier.height(15.dp))
                    list.forEach { item ->
                        NavigationDrawerItem(
                            label = { Text(text = item) },
                            selected = item == selectedItem.value,
                            onClick = {
                                scope.launch { drawerState.close() }
                                selectedItem.value = item
                                gotoSelected = true
                            },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )

                    }
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                val sumOfExpenses = homeViewModel.expSumArr!!.collectAsState(initial = 0f)
                val sumOfIncomes = homeViewModel.incomeSumArr!!.collectAsState(initial = 0f)
                val balanceAmount = remember {
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

                        ),
//            contentAlignment = Alignment.Center
                ) {

                    balanceAmount.value = sumOfIncomes.value - sumOfExpenses.value

                    IconButton(
                        onClick = {
                            scope.launch { drawerState.open() }


                        },
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(start = 5.dp)
                            .scale(1.4f)
                    ) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Open Menu")
                    }
                    Column {
                        Spacer(modifier = Modifier.height(40.dp))

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Text(text = "CURRENT BALANCE")
                            Text(
                                text = "₹ ${getNumber(balanceAmount.value.toString())}",
                                textAlign = TextAlign.Center,
                                fontSize = 40.sp,
                                fontFamily = Inter_SemiBold
                            )
                            Text(text = "Month : $currentMonth ")


                        }
                        Spacer(modifier = Modifier.height(40.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp)
                        ) {
                            Column {


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

fun sendEmail(context: Context) {
    val addresses= arrayOf("bharathayinala@gmail.com","")
    val subject= "Expense Tracker FeedBack"
    try {


        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/html"
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.setPackage("com.google.android.gm")
        context.startActivity(intent)
    }catch (e:Exception){
        e.printStackTrace()
        Toast.makeText(context, "No Email App Found", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun IncomeExpenseTxt(income: Float, Expense: Float) {
    val brush = Brush.linearGradient(
        listOf(
            Money1exp,
            Money2exp
        )
    )
    val brush2 = Brush.linearGradient(
        listOf(
            Money1inc,
            Money2inc
        )
    )
    Column {
        Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
            ) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .clip(RoundedCornerShape(50))
                    .background(brush2)
                    .padding(end = 10.dp)

            )
            Spacer(modifier = Modifier.width(10.dp))
//            Text(
//                text = "Income : ",
//                fontSize = 18.sp
//            )
            Text(
                text = "₹ ${getNumber(income.toString())}",
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.8f)

                ,
                maxLines = 1,
                fontFamily = Inter_Bold
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .clip(RoundedCornerShape(50))
                    .background(brush)
                    .padding(end = 10.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
//            Text(
//                text = "Expense : ",
//                fontSize = 18.sp
//
//            )
            Text(
                text = "₹ ${getNumber(Expense.toString())}",
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.8f),
                maxLines = 1,
                fontFamily = Inter_Bold,
                overflow = TextOverflow.Ellipsis
            )
        }
    }


}

@Composable
@Preview(showBackground = true)
fun Preview() {
    HomeScreen()
}


@Composable
private fun ShowPercentage(percentage: Float) {
    var showtext by remember {
        mutableStateOf(false)
    }
    Text(text = "Tap to show Income Analysis", modifier = Modifier
        .clickable {
            showtext = !showtext
        }
        .alpha(0.5f),
        textAlign = TextAlign.Center,
        fontSize = 11.sp,
        fontFamily = Lato_Regular,
        color = MaterialTheme.colorScheme.inverseSurface
    )
    if (showtext) {
        PercentageText(percentage = percentage)


    }
}