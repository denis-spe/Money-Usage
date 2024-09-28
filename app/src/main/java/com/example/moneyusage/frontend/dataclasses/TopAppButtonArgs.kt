package com.example.moneyusage.frontend.dataclasses

import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color

data class TopAppButtonArgs(
    val label: String,
    val topBarTextColor: Color,
    val selectedButton: MutableState<String>,
    val onClick: () -> Unit,
    val styles: Styles = Styles(),
)
