package com.bharath.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.bharath.expensetracker.ui.theme.ExpenseTrackerTheme
import com.bharath.expensetracker.ui.theme.Navigation.SetupNavGraph
import com.bharath.expensetracker.ui.theme.onboarding.vm.SplashViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
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

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExpenseTrackerTheme {

    }
}