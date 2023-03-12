package pl.mclojek.carpify.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.mclojek.carpify.presentation.screen.*
import pl.mclojek.carpify.presentation.screen.ScreenRoutes.FEED_SCREEN_ROUTE
import pl.mclojek.carpify.presentation.screen.ScreenRoutes.FORGOT_PASSWORD_SCREEN_ROUTE
import pl.mclojek.carpify.presentation.screen.ScreenRoutes.LAKES_LIST_SCREEN_ROUTE
import pl.mclojek.carpify.presentation.screen.ScreenRoutes.LAKE_DETAILS_SCREEN_ROUTE
import pl.mclojek.carpify.presentation.screen.ScreenRoutes.LOGIN_SCREEN_ROUTE
import pl.mclojek.carpify.presentation.screen.ScreenRoutes.REGISTER_SCREEN_ROUTE
import pl.mclojek.carpify.presentation.theme.CarpifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarpifyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }
}

@Composable
fun Content() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable(LOGIN_SCREEN_ROUTE) {
            LoginScreen(
                navigateForward = {
                    navController.navigate(FEED_SCREEN_ROUTE)
                }, navigateRegister = {
                    navController.navigate(REGISTER_SCREEN_ROUTE)
                }, navigateForgetPass = {
                    navController.navigate(FORGOT_PASSWORD_SCREEN_ROUTE)
                }
            )
        }
        composable(REGISTER_SCREEN_ROUTE) {
            RegisterScreen(navigateBack = {
                navController.navigateUp()
            })
        }
        composable(FORGOT_PASSWORD_SCREEN_ROUTE) {
            ForgotPasswordScreen(navigateBack = {
                navController.navigateUp()
            })
        }
        composable(FEED_SCREEN_ROUTE) { FeedScreen() }
        composable(LAKES_LIST_SCREEN_ROUTE) { LakesListScreen() }
        composable(LAKE_DETAILS_SCREEN_ROUTE) { LakeDetailsScreen() }
    }
}
