package com.example.moneyusage.frontend.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.moneyusage.R
import com.example.moneyusage.frontend.dataclasses.AuthError

/**
 * Email authorization Outlined Text Field
 */
@Composable
fun EmailAuthTextField(
    label: String,
    placeholder: String? = null,
    input: MutableState<TextFieldValue>,
    isError: Boolean = false,
    isAuthButtonClick: Boolean = false,
    errorMessage: String = ""
){
    Column (
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ){

        // Outlined Text Field
        OutlinedTextField(
            value = input.value,
            onValueChange = {
                input.value = it
            },
            placeholder = {
                Text(text = placeholder ?: label)
            },
            label = {
                Text(text = label)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(R.color.authorization_color),
                focusedLabelColor = colorResource(R.color.authorization_color)
            ),
            isError = isError
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
