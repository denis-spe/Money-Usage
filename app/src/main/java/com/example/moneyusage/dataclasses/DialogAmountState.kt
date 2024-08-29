package com.example.moneyusage.dataclasses

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue

data class DialogAmountState(
    var income: MutableState<TextFieldValue>,
    var incomeDesc: MutableState<TextFieldValue>,

    var expense: MutableState<TextFieldValue>,
    var expenseDesc: MutableState<TextFieldValue>,

    var debt: MutableState<TextFieldValue>,
    var debtDesc: MutableState<TextFieldValue>,
    
    var lend: MutableState<TextFieldValue>,
    var lendDesc: MutableState<TextFieldValue>,
)
