package net.simplifiedcoding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import net.simplifiedcoding.post_api.MyApp
import net.simplifiedcoding.ui.MainViewModel
import net.simplifiedcoding.ui.UserList

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            NavGraph(navController, viewModel )
        }
    }
}


sealed class Screen(val route : String){
    object UserScreen : Screen(route = "user_screen")
    object MyApp : Screen(route = "my_app")
}


@Composable
fun NavGraph(
    navController : NavHostController,
    viewModel: MainViewModel
) {

    NavHost(
        navController = navController,
        startDestination = Screen.MyApp.route
    ) {

        composable(Screen.MyApp.route) {
            MyApp(navController)
        }

        composable(Screen.UserScreen.route) {
            UserList(viewModel = viewModel)
        }


    }


}
























