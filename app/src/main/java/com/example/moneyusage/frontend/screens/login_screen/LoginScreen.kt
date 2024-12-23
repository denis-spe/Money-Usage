package com.example.moneyusage.frontend.screens.login_screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyusage.R
import com.example.moneyusage.backend.models.services.impl.AccountServiceImpl
import com.example.moneyusage.frontend.components.AppTitle
import com.example.moneyusage.frontend.components.AuthButton
import com.example.moneyusage.frontend.components.AuthOutlineButton
import com.example.moneyusage.frontend.components.AuthTextButton
import com.example.moneyusage.frontend.components.EmailAuthTextField
import com.example.moneyusage.frontend.components.PasswordAuthTextField

class LoginScreen(
    private val viewModel: LoginScreenViewModel = LoginScreenViewModel(AccountServiceImpl())
) {

    @Composable
    fun EmailOrUsernameScreen(
        openAndPopUp: (String, String) -> Unit
    ){
        val emailInputState = remember {
            mutableStateOf(TextFieldValue(""))
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // App title
            AppTitle()

            Spacer(modifier = Modifier.height(10.dp))

            // Description text
            Text(
                text = "Sign in with Money Usage account",
                fontSize = 17.sp,
                fontWeight = FontWeight(450)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                // Email address or username textField
                EmailAuthTextField(
                    label = stringResource(R.string.loginEmailLabel),
                    input = emailInputState,
                    isAuthButtonClick = false,
                    errorMessage = "Enter an email or username"
                )

                // Forgot email text button row
                Row {
                    // Forget email
                    AuthTextButton(
                        text = "Forgot email?",
                    ) {
                        viewModel.onForgotEmailClick(openAndPopUp)
                    }
                    Spacer(modifier = Modifier.width(165.dp))
                }

                Spacer(modifier = Modifier.height(60.dp))

                // Register and next row
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    // Register button
                    AuthOutlineButton(label = "Register") {
                        viewModel.onRegisterClick(openAndPopUp)
                    }

                    Spacer(modifier = Modifier.width(95.dp))

                    // Next button
                    AuthButton(label = "Next") {
                        viewModel.onEmailNextClick(emailInputState, openAndPopUp)
                    }
                }
            }
        }
    }

    @Composable
    fun PasswordScreen(
        openAndPopUp: (String, String) -> Unit
    ){
        val username = "denis-spe"

        val passwordInputState = remember {
            mutableStateOf(TextFieldValue(""))
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // App title
            AppTitle()

            Spacer(modifier = Modifier.height(10.dp))

            // Description text
            Text(
                text = "Welcome back $username",
                fontSize = 17.sp,
                fontWeight = FontWeight(450)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                // Password textField
                PasswordAuthTextField(
                    label = "Password",
                    input = passwordInputState,
                    isAuthButtonClick = false,
                    errorMessage = "Enter an password"
                )

                Spacer(modifier = Modifier.height(60.dp))

                // Register and next row
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    // Forget password
                    AuthTextButton(
                        text = "Forgot password?",
                    ) {
                        viewModel.onForgotPasswordClick(openAndPopUp)
                    }

                    Spacer(modifier = Modifier.width(95.dp))

                    // Next button
                    AuthButton(label = "Next") {
                        viewModel.onPasswordNextClick(passwordInputState, openAndPopUp)
                    }
                }
            }
        }
    }
}


/**
 * SignInPage preview
 */
@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.S)
@Preview(
    showBackground = true,
    device = Devices.PIXEL_2_XL,
    showSystemUi = true
)
@Composable
fun LoginPageEmailPreview() {
    // Run the sign in page
    LoginScreen().EmailOrUsernameScreen(){
        _, _ ->
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(
    showBackground = true,
    device = Devices.PIXEL_2_XL,
    showSystemUi = true
)
@Composable
fun LoginPagePasswordPreview() {
    // Run the sign in page
    LoginScreen().PasswordScreen(){
        _, _ ->
    }
}
