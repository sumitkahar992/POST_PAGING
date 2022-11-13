package net.simplifiedcoding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import net.simplifiedcoding.onboarding.HomeScreen
import net.simplifiedcoding.onboarding.OnBoardingScreen
import net.simplifiedcoding.post_api.PostApi
import net.simplifiedcoding.ui.MainViewModel
import net.simplifiedcoding.ui.UserList

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

//        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            NavGraph(navController, viewModel)
        }
    }
}

@Composable
fun OnBoarding() {
    var showOnBoardingScreen by rememberSaveable { mutableStateOf(true) }

    if (showOnBoardingScreen) {
        OnBoardingScreen(onContinueBottomClicked = { showOnBoardingScreen = false })
    } else {
        HomeScreen()
    }


}


sealed class Screen(val route: String) {

    object UserScreen : Screen(route = "user_screen")
    object PostApi : Screen(route = "post_api")
    object OnBoarding : Screen(route = "on_boarding")
    object MainApp : Screen(route = "main_app")
}


@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: MainViewModel
) {

    NavHost(
        navController = navController,
        startDestination = Screen.MainApp.route
    ) {
        composable(Screen.MainApp.route) {
            MainApplication(navController)
        }

        composable(Screen.PostApi.route) {
            PostApi(navController)
        }

        composable(Screen.UserScreen.route) {
            UserList(viewModel = viewModel)
        }

        composable(Screen.OnBoarding.route) {
            OnBoarding()
        }


    }


}
























