package com.example.moneyusage.frontend.screens.landing_screen

import com.example.moneyusage.HOME_SCREEN
import com.example.moneyusage.LANDING_SCREEN
import com.example.moneyusage.LOGIN_EMAIL_SCREEN
import com.example.moneyusage.REGISTER_NAMES_SCREEN
import com.example.moneyusage.backend.models.services.interf.AccountService
import com.example.moneyusage.frontend.screens.AppViewModel
import javax.inject.Inject

class LandingScreenViewModel @Inject constructor(
    private val accountService: AccountService,
): AppViewModel() {
    fun onLoginClick(openAndPopUp: (String, String) -> Unit){
        openAndPopUp(LOGIN_EMAIL_SCREEN, LANDING_SCREEN)
    }

    fun onRegisterClick(openAndPopUp: (String, String) -> Unit){
        openAndPopUp(REGISTER_NAMES_SCREEN, LANDING_SCREEN)
    }
}