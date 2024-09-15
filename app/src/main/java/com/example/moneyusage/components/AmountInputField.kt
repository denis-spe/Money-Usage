package com.example.moneyusage.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.moneyusage.dataclasses.AmountButtonState
import com.example.moneyusage.helper.toMoneyFormat

@SuppressLint("RememberReturnType")
@Composable
fun AmountInputField(
    amountButtonState: MutableState<AmountButtonState>,
    state: MutableState<TextFieldValue>,
){
    val fontWeight = FontWeight.Bold
    val fontSize = 23.sp
    
    TextField(
        value = TextFieldValue(
            text = state.value.text,
            selection = TextRange(state.value.text.length)
        ),
        isError = amountButtonState.value == AmountButtonState.ERROR,
        onValueChange = {
            val text = it.text
            // Limit the number of dotes to 1 and the length to 14
            if (text.count { char -> char == '.' } < 2 && text.length < 16){
                if (text.contains(".")){
                    val number = text.split(".")
                    val wholeNumber = number[0]
                    val afterDecimal = number[1]
                    state.value = TextFieldValue(
                        "${wholeNumber.toMoneyFormat()}.${afterDecimal.take(2)}"
                    )
                } else {
                    state.value = TextFieldValue(
                        text.toMoneyFormat()
                    )
                }
            }

            amountButtonState.value = AmountButtonState.INITIAL
        },
        textStyle = TextStyle(
            fontWeight = fontWeight,
            fontSize = fontSize),
        placeholder = {
            Text(
                text = "0.00",
                fontWeight = fontWeight,
                fontSize = fontSize
            )
        },
        prefix = {
            Text(
                text = "R",
                fontWeight = FontWeight.Bold,
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
    )

}



@Preview
@Composable
fun InputTextComponentPreview(){
    AmountInputField(
        amountButtonState = remember {
            mutableStateOf(AmountButtonState.INITIAL)
        },
        state = remember {
        mutableStateOf(TextFieldValue("")) }
    )
}