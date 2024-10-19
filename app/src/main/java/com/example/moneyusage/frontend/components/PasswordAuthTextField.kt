package com.example.moneyusage.frontend.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.moneyusage.R
import com.example.moneyusage.frontend.dataclasses.AuthError

/**
 * Email authorization Outlined Text Field
 */
@Composable
fun PasswordAuthTextField(
    label: String,
    input: MutableState<TextFieldValue>,
    isError: Boolean = false,
    isAuthButtonClick: Boolean = false,
    errorMessage: String = ""
){
    Column (
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ){

        val colorResource = colorResource(id = R.color.authorization_color)

        // Outlined Text Field
        OutlinedTextField(
            value = input.value,
            onValueChange = {
                input.value = it
            },
            placeholder = {
                Text(text = label)
            },
            label = {
                Text(text = label)
            },
            isError = isError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource,
                focusedLabelColor = colorResource,
                focusedTrailingIconColor = colorResource,
            ),
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter =  painterResource(id = R.drawable.visibility),
                        contentDescription = "password visible icon",
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(5.dp))

        // Show error message
        if (isAuthButtonClick){
            AuthError(
                message = errorMessage
            ).Show()
        }
    }
}