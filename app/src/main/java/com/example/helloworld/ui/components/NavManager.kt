package com.example.helloworld.ui.components
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.helloworld.ui.screens.landing.LandingScreen
import com.example.helloworld.ui.screens.login.LoginScreen
import com.example.helloworld.ui.screens.register.RegisterScreen
import com.example.helloworld.ui.screens.shared.Screens

@Composable
fun BetPoliApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController,
        startDestination = Screens.LANDING.name
    ){
        composable(Screens.LANDING.name) {
            LandingScreen(
                onRegisterClicked = {
                    navController.navigate(Screens.REGISTER.name)
                },
                onLoginClicked = {
                    navController.navigate(Screens.LOGIN.name)
                }
            )
        }

        composable(Screens.REGISTER.name) {
            RegisterScreen()
        }

        composable(Screens.LOGIN.name) {
            LoginScreen()
        }
    }
}