package com.example.moneyusage.frontend.screens.register_screen

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import com.example.moneyusage.HOME_SCREEN
import com.example.moneyusage.LANDING_SCREEN
import com.example.moneyusage.REGISTER_EMAIL_SCREEN
import com.example.moneyusage.REGISTER_PASSWORD_SCREEN
import com.example.moneyusage.backend.models.services.interf.AccountService
import com.example.moneyusage.frontend.screens.AppViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class RegisterScreenViewModel@Inject constructor(
    private val accountService: AccountService
) : AppViewModel() {
    private val names = MutableStateFlow<List<String>>(emptyList())
    private val emailState = MutableStateFlow("")
    private val passwordState = MutableStateFlow("")
    private val confirmPasswordState = MutableStateFlow("")

    fun onNamesNextClick(
        firstNameState: MutableState<TextFieldValue>,
        lastNameState: MutableState<TextFieldValue>,
        openAndPopUp: (String, String) -> Unit
    ) {
        val firstName = firstNameState.value.text
        val lastName = lastNameState.value.text

        if (firstName.isNotEmpty()) {
            names.value = listOf(firstName, lastName)
            openAndPopUp(REGISTER_EMAIL_SCREEN, LANDING_SCREEN)
        }
    }

    fun onEmailNextClick(
        emailStateInput: MutableState<TextFieldValue>,
        openAndPopUp: (String, String) -> Unit
    ) {
        val email = emailStateInput.value.text
        if (email.isNotEmpty()) {
            emailState.value = email
            openAndPopUp(REGISTER_PASSWORD_SCREEN, LANDING_SCREEN)
        }
    }

    fun onPasswordNextClick(
        passwordStateInput: MutableState<TextFieldValue>,
        confirmPasswordStateInput: MutableState<TextFieldValue>,
        openAndPopUp: (String, String) -> Unit
    ){
        val password = passwordStateInput.value.text
        val confirmPassword = confirmPasswordStateInput.value.text

        if (password.isNotEmpty() && confirmPassword.isNotEmpty() &&
            password == confirmPassword && password.length >= 8 &&
            password[0].isUpperCase() && password[0].isLetter()) {
            passwordState.value = password
            confirmPasswordState.value = confirmPassword

            launchCatching {
                accountService.register(emailState.value, passwordState.value, names.value)
                openAndPopUp(HOME_SCREEN, LANDING_SCREEN)
            }
        }
    }
}