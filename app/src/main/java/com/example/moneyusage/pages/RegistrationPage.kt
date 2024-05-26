package com.example.moneyusage.pages

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyusage.components.AppTitle
import com.example.moneyusage.components.AuthButton
import com.example.moneyusage.components.DateTextField
import com.example.moneyusage.components.DropDownComponent
import com.example.moneyusage.components.EmailAuthTextField
import com.example.moneyusage.components.NameTextField
import com.example.moneyusage.components.PasswordAuthTextField
import com.example.moneyusage.dataclasses.AuthDataclass

class RegistrationPage(
    private val input: MutableState<TextFieldValue>
){
    private val authDataclass = AuthDataclass()

    @Composable
    fun NamesPage(){
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
                    input = input,
                    isAuthButtonClick = false,
                    errorMessage = "Enter an email or username"
                )

                NameTextField(
                    label = "Last name (Optional)",
                    input = input,
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
                    }
                }
            }
        }
    }

    @Composable
    fun MoreInformation(){
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
                text = "More Information",
                fontSize = authDataclass.subTitleFontSize,
                fontWeight = authDataclass.subTitleFontWeight
            )

            Text(
                text = "Enter your year of birth and gender",
                fontSize = authDataclass.descFontSize,
                fontWeight = authDataclass.descFontWeight
            )

            Spacer(modifier = Modifier.height(30.dp))

            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ){
                val year = listOf(1997, 1998, 1999, 2001)
                val selectedTextState = remember {
                    mutableStateOf(TextFieldValue(""))
                }

                val dayState = remember {
                    mutableStateOf(TextFieldValue(""))
                }

                Row {
                    // Month drop down
                    DropDownComponent(
                        label = "Month",
                        items = year,
                        selectedText = selectedTextState,
                        modifier = Modifier.width(160.dp)
                        )

                    Spacer(modifier = Modifier.width(10.dp))

                    // Day Outline TextField
                    DateTextField(
                        label = "Day",
                        value = dayState,
                        modifier = Modifier.width(90.dp))

                    Spacer(modifier = Modifier.width(10.dp))
                    // Year Outline TextField
                    DateTextField(label = "Year",
                        value = dayState,
                        modifier = Modifier.width(90.dp)
                        )
                }

                DropDownComponent(
                    label = "Gender",
                    items = year,
                    selectedText = selectedTextState,
                    modifier = Modifier.width(360.dp)
                )

                Spacer(modifier = Modifier.height(50.dp))

                // Register and next row
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .width(368.dp)
                ){
                    // Next button
                    AuthButton(label = "Next") {
                    }
                }
            }
        }
    }

    @Composable
    fun EmailCreationPage(){
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
                    label = "Create Money Usage email",
                    input = input,
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
                    }
                }
            }
        }
    }

    @Composable
    fun PasswordPage(){
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
                    input = input,
                    isAuthButtonClick = false,
                    errorMessage = "Enter an password"
                )

                // Password textField
                PasswordAuthTextField(
                    label = "Confirm password",
                    input = input,
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

                    }
                }
            }
        }
    }

    /**
     * Run the registration page
     */
    @Composable
    fun Run(){

    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(
    showBackground = true,
    device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420",
    showSystemUi = true
)
@Composable
fun RegistrationPageNamesPreview() {
    // MutableState object
    val input = mutableStateOf(TextFieldValue(""))

    // Run the sign in page
    RegistrationPage(input).NamesPage()
}

@SuppressLint("UnrememberedMutableState")
@Preview(
    showBackground = true,
    device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420",
    showSystemUi = true
)
@Composable
fun RegistrationPageMoreInformationPreview() {
    // MutableState object
    val input = mutableStateOf(TextFieldValue(""))

    // Run the sign in page
    RegistrationPage(input).MoreInformation()
}

@SuppressLint("UnrememberedMutableState")
@Preview(
    showBackground = true,
    device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420",
    showSystemUi = true
)
@Composable
fun RegistrationPageEmailPreview() {
    // MutableState object
    val input = mutableStateOf(TextFieldValue(""))

    // Run the sign in page
    RegistrationPage(input).EmailCreationPage()
}

@SuppressLint("UnrememberedMutableState")
@Preview(
    showBackground = true,
    device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420",
    showSystemUi = true
)
@Composable
fun RegistrationPagePasswordPreview() {
    // MutableState object
    val input = mutableStateOf(TextFieldValue(""))

    // Run the sign in page
    RegistrationPage(input).PasswordPage()
}