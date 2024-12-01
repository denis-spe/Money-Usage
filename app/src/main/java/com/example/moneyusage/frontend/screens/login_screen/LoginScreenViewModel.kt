package com.example.moneyusage.frontend.screens.login_screen

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import com.example.moneyusage.HOME_SCREEN
import com.example.moneyusage.LOGIN_EMAIL_SCREEN
import com.example.moneyusage.LOGIN_PASSWORD_SCREEN
import com.example.moneyusage.REGISTER_NAMES_SCREEN
import com.example.moneyusage.backend.models.services.interf.AccountService
import com.example.moneyusage.frontend.screens.AppViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class LoginScreenViewModel @Inject constructor(
    private val accountService: AccountService
) : AppViewModel() {
    // Email input states
    private val emailInputState = MutableStateFlow(TextFieldValue(""))

    fun onEmailNextClick(
        inputState: MutableState<TextFieldValue>,
        openAndPopUp: (String, String) -> Unit
    ) {
        // Get email from input
        val email = inputState.value.text

        if (email.isNotEmpty()){
            emailInputState.value = inputState.value
            openAndPopUp(LOGIN_PASSWORD_SCREEN, LOGIN_EMAIL_SCREEN)
        }
    }

    fun onRegisterClick(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(REGISTER_NAMES_SCREEN, LOGIN_EMAIL_SCREEN)
    }

    fun onPasswordNextClick(
        inputState: MutableState<TextFieldValue>,
        openAndPopUp: (String, String) -> Unit
    ) {
        // Get password from input
        val password = inputState.value.text

        if (password.isNotEmpty()){
            launchCatching {
                accountService.login(emailInputState.value.text, password)
                openAndPopUp(HOME_SCREEN, LOGIN_PASSWORD_SCREEN)
            }
        }
    }

    fun onForgotEmailClick(openAndPopUp: (String, String) -> Unit) {
        TODO("Open forgot email screen")
    }

    fun onForgotPasswordClick(openAndPopUp: (String, String) -> Unit) {
        TODO("Open forgot password screen")
    }
}