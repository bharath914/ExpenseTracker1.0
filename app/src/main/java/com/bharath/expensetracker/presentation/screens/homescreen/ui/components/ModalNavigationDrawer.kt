package com.bharath.expensetracker.presentation.screens.homescreen.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable

fun ModalNavigationDrawer_Home() {
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
    )
    {

    }

}