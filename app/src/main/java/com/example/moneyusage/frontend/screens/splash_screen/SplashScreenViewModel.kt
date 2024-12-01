package com.example.moneyusage.frontend.screens.splash_screen

import com.example.moneyusage.HOME_SCREEN
import com.example.moneyusage.LANDING_SCREEN
import com.example.moneyusage.SPLASH_SCREEN
import com.example.moneyusage.backend.models.services.interf.AccountService
import com.example.moneyusage.frontend.screens.AppViewModel
import javax.inject.Inject

class SplashScreenViewModel @Inject constructor(
    private val accountService: AccountService
) : AppViewModel() {

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        if (accountService.hasUser()) openAndPopUp(HOME_SCREEN, SPLASH_SCREEN)
        else openAndPopUp(LANDING_SCREEN, SPLASH_SCREEN)
    }
}