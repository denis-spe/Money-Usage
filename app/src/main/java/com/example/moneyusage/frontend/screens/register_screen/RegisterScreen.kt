package com.example.moneyusage.frontend.screens.register_screen

import android.annotation.SuppressLint
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.moneyusage.R
import com.example.moneyusage.backend.models.services.impl.AccountServiceImpl
import com.example.moneyusage.frontend.components.AppTitle
import com.example.moneyusage.frontend.components.AuthButton
import com.example.moneyusage.frontend.components.EmailAuthTextField
import com.example.moneyusage.frontend.components.NameTextField
import com.example.moneyusage.frontend.components.PasswordAuthTextField
import com.example.moneyusage.frontend.dataclasses.AuthDataclass
import com.example.moneyusage.frontend.helper.NavRoutes
import com.google.firebase.auth.FirebaseAuth

class RegisterScreen(
    private val viewModel: RegisterScreenViewModel = RegisterScreenViewModel(AccountServiceImpl())
) {
    private val authDataclass = AuthDataclass()

    @Composable
    fun NamesScreen(
        openAndPopUp: (String, String) -> Unit
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val firstNameState = remember { mutableStateOf(TextFieldValue("")) }
            val lastNameState = remember { mutableStateOf(TextFieldValue("")) }

            // App title
            AppTitle()

            Spacer(modifier = Modifier.height(10.dp))

            // Description text
            Text(
                text = "Create a Money Usage account",
                fontSize = authDataclass.subTitleFontSize,
                fontWeight = authDataclass.subTitleFontWeight
            )

            Text(
                text = "Enter your names",
                fontSize = authDataclass.descFontSize,
                fontWeight = authDataclass.descFontWeight
            )

            Spacer(modifier = Modifier.height(30.dp))

            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                // Email address or username textField
                NameTextField(
                    label = "First name",
                    input = firstNameState,
                    isAuthButtonClick = false,
                    errorMessage = "Enter an email or username"
                )

                NameTextField(
                    label = "Last name (Optional)",
                    input = lastNameState,
                    isAuthButtonClick = false,
                    errorMessage = "Enter an email or username"
                )

                Spacer(modifier = Modifier.height(60.dp))

                // Register and next row
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .width(280.dp)
                ){
                    // Next button
                    AuthButton(label = "Next") {
                        viewModel.onNamesNextClick(firstNameState, lastNameState, openAndPopUp)
                    }
                }
            }
        }
    }

    @Composable
    fun EmailCreationScreen(openAndPopUp: (String, String) -> Unit) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val emailState = remember { mutableStateOf(TextFieldValue("")) }

            // App title
            AppTitle()

            Spacer(modifier = Modifier.height(10.dp))

            // Description text
            Text(
                text = "Create email",
                fontSize = authDataclass.subTitleFontSize,
                fontWeight = authDataclass.subTitleFontWeight
            )
            Text(
                text="for your Money Usage account",
                fontSize = authDataclass.descFontSize,
                fontWeight = authDataclass.descFontWeight
            )

            Spacer(modifier = Modifier.height(30.dp))

            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                // Email address or username textField
                EmailAuthTextField(
                    label = stringResource(R.string.email_creation_label),
                    placeholder = stringResource(R.string.email_creation_placeholder),
                    input = emailState,
                    isAuthButtonClick = false,
                    errorMessage = "Enter an email or username"
                )

                Spacer(modifier = Modifier.height(60.dp))

                // Next button row
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .width(290.dp)
                ){
                    // Next button
                    AuthButton(label = "Next") {
                        viewModel.onEmailNextClick(emailState, openAndPopUp)
                    }
                }
            }
        }
    }

    @Composable
    fun PasswordScreen(
        openAndPopUp: (String, String) -> Unit
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val passwordState = remember { mutableStateOf(TextFieldValue("")) }
            val confirmPasswordState = remember { mutableStateOf(TextFieldValue("")) }

            // App title
            AppTitle()

            Spacer(modifier = Modifier.height(10.dp))

            // Description text
            Text(
                text = "Create  a strong password",
                fontSize = authDataclass.subTitleFontSize,
                fontWeight = authDataclass.subTitleFontWeight
            )

            Text(
                text = "for your Money Usage account",
                fontSize = authDataclass.descFontSize,
                fontWeight = authDataclass.descFontWeight
            )

            Spacer(modifier = Modifier.height(30.dp))

            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                // Password textField
                PasswordAuthTextField(
                    label = "Password",
                    input = passwordState,
                    isAuthButtonClick = false,
                    errorMessage = "Enter an password"
                )

                // Password textField
                PasswordAuthTextField(
                    label = "Confirm password",
                    input = confirmPasswordState,
                    isAuthButtonClick = false,
                    errorMessage = "Enter an password"
                )

                Spacer(modifier = Modifier.height(60.dp))

                // Register and next row
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .width(280.dp)
                ){
                    // Next button
                    AuthButton(label = "Next") {
                        viewModel.onPasswordNextClick(
                            passwordState,
                            confirmPasswordState,
                            openAndPopUp)
                    }
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp,dpi=420",
    showSystemUi = true
)
@Composable
fun RegistrationScreenNamesPreview() {


    // Run the sign in page
    RegisterScreen().NamesScreen(){
        _,_ ->
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp,dpi=420",
    showSystemUi = true
)
@Composable
fun RegistrationScreenEmailPreview() {
    // Run the sign in page
    RegisterScreen().EmailCreationScreen { _, _ ->
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp,dpi=420",
    showSystemUi = true
)
@Composable
fun RegistrationScreenPasswordPreview() {
    // Run the sign in page
    RegisterScreen().PasswordScreen(){
        _,_ ->
    }
}