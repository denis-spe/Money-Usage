package com.example.moneyusage.frontend.dataclasses

import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.example.moneyusage.frontend.helper.Period

data class TopAppButtonArgs(
    val label: Period,
    val topBarTextColor: Color,
    val selectedButton: MutableState<Period>,
    val onClick: () -> Unit,
    val styles: Styles = Styles(),
)
