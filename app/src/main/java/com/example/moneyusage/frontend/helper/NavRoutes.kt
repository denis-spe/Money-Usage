package com.example.moneyusage.frontend.helper

sealed class NavRoutes(val route: String) {
    data object Home : NavRoutes(Screen.HOME.name)
    data object LoginEmail : NavRoutes(Screen.LOGIN_EMAIL.name)
    data object LoginPassword : NavRoutes(Screen.LOGIN_PASSWORD.name)
    data object LandingPage : NavRoutes(Screen.LANDING_PAGE.name)
    data object RegisterNames: NavRoutes(Screen.REGISTER_NAMES.name)
    data object RegisterMoreInfo: NavRoutes(Screen.REGISTER_MORE_INFO.name)
    data object RegisterEmail: NavRoutes(Screen.REGISTER_EMAIL.name)
    data object RegisterPassword: NavRoutes(Screen.REGISTER_PASSWORD.name)
}