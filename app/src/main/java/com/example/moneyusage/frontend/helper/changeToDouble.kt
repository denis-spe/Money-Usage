package com.example.moneyusage.frontend.helper

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue

val MutableState<TextFieldValue>.changeToDouble: Double
    get() {
        val value = this.value.text
        val builder = StringBuilder()
        value.forEach {
            if (it.isDigit() || it == '.') {
                builder.append(it)
            }
        }
        return builder.toString().toDouble()
    }