package com.example.betpoli.ui.components
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.betpoli.ui.screens.landing.LandingScreen
import com.example.betpoli.ui.screens.login.LoginScreen
import com.example.betpoli.ui.screens.matches.MatchesScreen
import com.example.betpoli.ui.screens.shared.Screens

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BetPoliApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController,
        startDestination = Screens.MATCHES.name
    ){
        composable(Screens.LANDING.name) {
            LandingScreen(
                onLoginClicked = {
                    navController.navigate(Screens.LOGIN.name)
                }
            )
        }

        composable(Screens.LOGIN.name) {
            LoginScreen(
                onceLogged = {
                    navController.navigate(Screens.MATCHES.name)
                }
            )
        }
        composable(Screens.MATCHES.name) {
            MatchesScreen()
        }
    }
}