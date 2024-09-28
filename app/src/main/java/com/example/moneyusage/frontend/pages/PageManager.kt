package com.example.moneyusage.frontend.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moneyusage.frontend.helper.NavRoutes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PageManager(
    currentUser: FirebaseUser? = null,
    auth: FirebaseAuth? = null,
){

    // Initializer the nav controller state
    val navController = rememberNavController()

    // Instantiate the Login page.
    val loginPage = LoginPage(navController, auth)

    // Instantiate the Register page.
    val registerPage = RegistrationPage(navController, auth)

    // Instantiate the Home page.
    val homePage = HomePage(navController)

    // Navigation host.
    NavHost(
        navController = navController,
        startDestination = if (currentUser != null) NavRoutes.Home.route
        else NavRoutes.LandingPage.route
    ) {
        // Landing page
        composable(NavRoutes.LandingPage.route){
            LandingPage(navController)
        }

        // Home page
        composable(NavRoutes.Home.route){
            homePage.HomePage()
        }

        // Login email page
        composable(NavRoutes.LoginEmail.route){
            loginPage.EmailOrUsernamePage()
        }

        // Registration password page
        composable(NavRoutes.LoginPassword.route){
            loginPage.PasswordPage()
        }

        // Registration names page
        composable(NavRoutes.RegisterNames.route){
            registerPage.NamesPage()
        }

        // Registration more information page
        composable(NavRoutes.RegisterMoreInfo.route){
            registerPage.MoreInformation()
        }

        // Registration email page
        composable(NavRoutes.RegisterEmail.route){
            registerPage.EmailCreationPage()
        }

        // Login password page
        composable(NavRoutes.RegisterPassword.route){
            registerPage.PasswordPage()
        }
    }
}