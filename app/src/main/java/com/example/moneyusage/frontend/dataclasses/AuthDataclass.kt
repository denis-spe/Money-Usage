package com.example.moneyusage.frontend.dataclasses

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class AuthDataclass(
    val subTitleFontSize: TextUnit = 17.sp,
    val subTitleFontWeight: FontWeight = FontWeight(700),

    val descFontSize: TextUnit = 16.sp,
    val descFontWeight: FontWeight = FontWeight(400)
)
