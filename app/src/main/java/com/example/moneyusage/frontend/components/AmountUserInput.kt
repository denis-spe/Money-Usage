package com.example.moneyusage.frontend.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AmountUserInput(
    state: MutableState<TextFieldValue>,
    label: String,
    placeholder: String,
){
    // Search font size
    val searchFontSize = 16.sp

    TextField(
        value = state.value,
        label = {
            Text(
                label,
                fontWeight= FontWeight.Bold
            )
        },
        onValueChange = {
            state.value = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 18.dp),
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        textStyle = TextStyle(fontSize = searchFontSize),
        placeholder = {
            Text(
                text = placeholder,
                fontSize = searchFontSize,
                fontWeight= FontWeight.Bold
            )
        },
    )
}