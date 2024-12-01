package com.example.moneyusage.frontend.screens

import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController

@Stable
class AppNavState(val navController: NavHostController) {
    /**
     * Pop back stack to a route.
     */
    fun popUp(){
        navController.popBackStack()
    }

    /**
     * Navigate to a route.
     */
    fun navigate(route: String){
        navController.navigate(route){
            launchSingleTop = true
        }
    }

    /**
     * Navigate to a route and clear the back stack.
     */
    fun navigateAndPopUp(route: String, popUp: String){
        navController.navigate(route){
            launchSingleTop = true
            popUpTo(popUp){
                inclusive = true
            }
        }
    }

    fun clearAndNavigate(route: String){
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) {
                inclusive = true
            }
        }
    }

}