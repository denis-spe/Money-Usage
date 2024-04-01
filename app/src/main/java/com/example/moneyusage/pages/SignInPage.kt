package com.example.moneyusage.pages

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyusage.components.AppTitle
import com.example.moneyusage.components.AuthButton
import com.example.moneyusage.components.AuthOutlineButton
import com.example.moneyusage.components.AuthTextButton
import com.example.moneyusage.components.EmailAuthTextField
import com.example.moneyusage.components.PasswordAuthTextField

class SignInPage(
    private val input: MutableState<TextFieldValue>
) {

    @Composable
    fun EmailOrUsernamePage(){
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
                    label = "Email or username",
                    input = input,
                    isAuthButtonClick = false,
                    errorMessage = "Enter an email or username"
                )

                // Forgot email text button row
                Row {
                    // Forget email
                    AuthTextButton(
                        text = "Forgot email?",
                    ) {
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
                    }

                    Spacer(modifier = Modifier.width(95.dp))

                    // Next button
                    AuthButton(label = "Next") {

                    }
                }
            }
        }
    }

    @Composable
    fun PasswordPage(){
        val username = "denis-spe"

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
                    input = input,
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
                    }

                    Spacer(modifier = Modifier.width(95.dp))

                    // Next button
                    AuthButton(label = "Next") {

                    }
                }
            }
        }
    }

    /**
     * Run the sign in page
     */
    @Composable
    fun Run() {
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
fun SignInPageEmailPreview() {
    // MutableState object
    val input = mutableStateOf(TextFieldValue(""))

    // Run the sign in page
    SignInPage(input).EmailOrUsernamePage()
}

@SuppressLint("UnrememberedMutableState")
@Preview(
    showBackground = true,
    device = Devices.PIXEL_2_XL,
    showSystemUi = true
)
@Composable
fun SignInPagePasswordPreview() {
    // MutableState object
    val input = mutableStateOf(TextFieldValue(""))

    // Run the sign in page
    SignInPage(input).PasswordPage()
}
