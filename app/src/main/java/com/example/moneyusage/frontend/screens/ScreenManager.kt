package com.example.moneyusage.frontend.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moneyusage.HOME_SCREEN
import com.example.moneyusage.LANDING_SCREEN
import com.example.moneyusage.LOGIN_EMAIL_SCREEN
import com.example.moneyusage.LOGIN_PASSWORD_SCREEN
import com.example.moneyusage.REGISTER_EMAIL_SCREEN
import com.example.moneyusage.REGISTER_NAMES_SCREEN
import com.example.moneyusage.REGISTER_PASSWORD_SCREEN
import com.example.moneyusage.SPLASH_SCREEN
import com.example.moneyusage.frontend.screens.home_screen.HomeScreen
import com.example.moneyusage.frontend.screens.landing_screen.LandingScreen
import com.example.moneyusage.frontend.screens.login_screen.LoginScreen
import com.example.moneyusage.frontend.screens.register_screen.RegisterScreen
import com.example.moneyusage.frontend.screens.splash_screen.SplashScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun ScreenManager() {

    // Navigation state
    val appState = rememberAppState()

    // Navigation host.
    NavHost(
        navController = appState.navController,
        startDestination = SPLASH_SCREEN
    ) {
        appGraph(appState)
    }
}

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        AppNavState(navController)
    }

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.appGraph(appState: AppNavState) {

    // Initialize login screen
    val loginScreen = LoginScreen()

    // Initialize register screen
    val registerScreen = RegisterScreen()

    // Splash screen (Loading screen)
    composable(SPLASH_SCREEN) {
        SplashScreen(
            openAndPopUp = { route, popUp ->
                appState.navigateAndPopUp(route, popUp)
            }
        )
    }

    // Landing screen
    composable(LANDING_SCREEN) {
        LandingScreen { route, popUp ->
                appState.navigateAndPopUp(route, popUp)
        }
    }

    // Login email screen
    composable(LOGIN_EMAIL_SCREEN) {
        loginScreen.EmailOrUsernameScreen { route, popUp ->
                appState.navigateAndPopUp(route, popUp)
        }
    }

    // Login email screen
    composable(LOGIN_PASSWORD_SCREEN) {
        loginScreen.PasswordScreen { route, popUp ->
                appState.navigateAndPopUp(route, popUp)
        }
    }

    // Register names screen
    composable(REGISTER_NAMES_SCREEN) {
        registerScreen.NamesScreen { route, popUp ->
                appState.navigateAndPopUp(route, popUp)
            }
    }

    // Register email screen
    composable(REGISTER_EMAIL_SCREEN) {
        registerScreen.EmailCreationScreen { route, popUp ->
                appState.navigateAndPopUp(route, popUp)
            }
    }

    // Register password screen
    composable(REGISTER_PASSWORD_SCREEN) {
        registerScreen.PasswordScreen(
            openAndPopUp = { route, popUp ->
                appState.navigateAndPopUp(route, popUp)
            }
        )
    }

    // Home screen
    composable(HOME_SCREEN) {
        HomeScreen(
            openScreen = {route -> appState.navigate(route)},
            restartApp = {route -> appState.clearAndNavigate(route)}
        )
    }

}