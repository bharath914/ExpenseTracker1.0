package com.bharath.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.bharath.expensetracker.ui.theme.ExpenseTrackerTheme
import com.bharath.expensetracker.ui.theme.Navigation.SetupNavGraph
import com.bharath.expensetracker.ui.theme.onboarding.vm.SplashViewModel
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
    lateinit var splashViewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition{

            !splashViewModel.isLoading.value
            }

        setContent {
            ExpenseTrackerTheme {
                val screen by splashViewModel.startDestination
               val controller= rememberNavController()
                SetupNavGraph(navController = controller,screen)
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
}



