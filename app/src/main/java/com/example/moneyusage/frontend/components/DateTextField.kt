package com.example.moneyusage.frontend.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.example.moneyusage.R

@Composable
fun DateTextField(label: String,
                  value: MutableState<TextFieldValue>,
                  modifier: Modifier = Modifier){

    val colorResource = colorResource(id = R.color.authorization_color)

    OutlinedTextField(
        value = value.value,
        onValueChange = {},
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource,
            focusedLabelColor = colorResource,
            focusedTrailingIconColor = colorResource,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number),
        label = {
            Text(text = label)
        }
        )
}