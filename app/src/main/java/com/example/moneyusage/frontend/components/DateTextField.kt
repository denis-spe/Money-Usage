package com.example.moneyusage.frontend.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun DateTextField(label: String,
                  value: MutableState<TextFieldValue>,
                  modifier: Modifier = Modifier){
    OutlinedTextField(
        value = value.value,
        onValueChange = {},
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number),
        label = {
            Text(text = label)
        }
        )
}