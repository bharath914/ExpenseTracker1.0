package com.bharath.expensetracker.presentation.screens.khata

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bharath.expensetracker.MainViewModel
import com.bharath.expensetracker.presentation.events.KhataAddEvents
import com.bharath.expensetracker.presentation.screens.khata.viewmodel.KhataAddViewModel

@Composable
fun KhataAddScreen(
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
) {

    val viewModel = hiltViewModel<KhataAddViewModel>()
    Content(khataAddViewModel = viewModel,navHostController)

}


@Composable
private fun Content(
    khataAddViewModel: KhataAddViewModel,
    navHostController: NavHostController
) {

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val onSaved :(bool:Boolean) ->Unit = remember {
        return@remember khataAddViewModel::saveToDb
    }
    Scaffold (
        topBar = {
            KhataAddTopBar(onBackEntered = {
                navHostController.navigateUp()
            }, onClickSaved = {

            })
        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            val title by khataAddViewModel.titleName.collectAsStateWithLifecycle(lifecycle = lifecycle)
            val amount by khataAddViewModel.amount.collectAsStateWithLifecycle(lifecycle = lifecycle)
            val isDebit by khataAddViewModel.isDebit.collectAsStateWithLifecycle(lifecycle = lifecycle)
            val date by khataAddViewModel.date.collectAsStateWithLifecycle(lifecycle = lifecycle)
            val modeOfPay by khataAddViewModel.paymentMethod.collectAsStateWithLifecycle(lifecycle = lifecycle)
            val desc by khataAddViewModel.description.collectAsStateWithLifecycle(lifecycle = lifecycle)

            val onTitleEntered: (value: String) -> Unit = remember {
                return@remember {
                    khataAddViewModel.onEvent(KhataAddEvents.onTitleEntered(it))
                }
            }

            val onAmountEntered: (value: String) -> Unit = remember {
                return@remember {
                    khataAddViewModel.onEvent(KhataAddEvents.onAmountEntered(it))
                }
            }

            val onDescEntered: (value: String) -> Unit = remember {
                return@remember {
                    khataAddViewModel.onEvent(KhataAddEvents.onDescEntered(it))
                }
            }

            val onModeOfPay: (value: String) -> Unit = remember {
                return@remember {
                    khataAddViewModel.onEvent(KhataAddEvents.onPaymentModeEntered(it))
                }
            }

            val onDateEntered: (value: String) -> Unit = remember {
                return@remember {
                    khataAddViewModel.onEvent(KhataAddEvents.onDateEntered(it))
                }
            }

            val onIsDebitChanged: (value: Boolean) -> Unit = remember {
                return@remember {
                    khataAddViewModel.onEvent(KhataAddEvents.isDebitChanged(it))
                }
            }


            TextField(value = title, onValueChange = {
                onTitleEntered(it)
            })

            TextField(value = amount, onValueChange = {
                onAmountEntered(it)
            })

            TextField(value = modeOfPay, onValueChange = {
                onModeOfPay(it)
            })

            TextField(value = desc, onValueChange = {
                onDescEntered(it)
            })

        }
    }
}


@Composable
fun KhataAddTopBar(
    onBackEntered: () -> Unit,
    onClickSaved: () -> Unit,
) {


    TopAppBar {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButton(onClick = { onBackEntered() }) {
                Icon(Icons.Outlined.ArrowBackIosNew, contentDescription = null)
            }

            IconButton(onClick = { onClickSaved() }) {
                Icon(imageVector = Icons.Outlined.Check, contentDescription = null)
            }
        }
    }
}