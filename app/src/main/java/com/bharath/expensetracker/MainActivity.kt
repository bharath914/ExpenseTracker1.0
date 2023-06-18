package com.bharath.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.bharath.expensetracker.screens.settings.viewmodel.SettingsVm
import com.bharath.expensetracker.screens.viewmodel.HomeViewModel
import com.bharath.expensetracker.ui.theme.CustomExpenseTrackerTheme
import com.bharath.expensetracker.ui.theme.ExpenseTrackerTheme
import com.bharath.expensetracker.uielements.util.BottomNavigationBarCus
import com.bharath.expensetracker.uielements.util.NavHostContainer
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalAnimationApi
@ExperimentalPagerApi
class MainActivity : ComponentActivity() {

  @Inject
  lateinit var homeViewModel:HomeViewModel
  @Inject
  lateinit var settingsVm: SettingsVm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            homeViewModel.isLoadingWhileFetching.value
        }


        val bool = settingsVm.amoledTheme.value

        setContent {

            if ( settingsVm.amoledTheme.value){
               Custom()
            }
            else {
               NOnCustom()

            }

//                ExpenseTrackerTheme() {
//
//
//                val navController = rememberNavController()
//                Surface(color = MaterialTheme.colorScheme.background) {
//                    Scaffold (
//                        bottomBar = {
//                            BottomNavigationBarCus(navHostController = navController)
//                        }, content ={
//                            NavHostContainer(navHostController = navController, paddingValues =it )
//                        }
//                    )
//                }
//
//            }

        }
    }

    @Composable
    fun Custom() {
        CustomExpenseTrackerTheme() {


            val navController = rememberNavController()
            Surface(color = MaterialTheme.colorScheme.background) {
                Scaffold(
                    bottomBar = {
                        BottomNavigationBarCus(navHostController = navController)
                    }, content = {
                        NavHostContainer(
                            navHostController = navController,
                            paddingValues = it
                        )
                    }
                )
            }

        }
    }
    @Composable
    fun NOnCustom(){
        ExpenseTrackerTheme() {

            val navController = rememberNavController()
            Surface(color = MaterialTheme.colorScheme.background) {
                Scaffold (
                    bottomBar = {
                        BottomNavigationBarCus(navHostController = navController)
                    }, content ={
                        NavHostContainer(navHostController = navController, paddingValues =it )
                    }
                )
            }

        }

    }
}



