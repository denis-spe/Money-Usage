package com.example.moneyusage.frontend.dataclasses

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Styles(
    val buttonBackgroundColor: Color = Color.LightGray.copy(0.3f),
    val clickedButtonColor: Color = Color(18, 171, 240, 255),
    val buttonTextColor: Color = Color.Black.copy(0.6f),
    val darkButtonTextColor: Color = Color.White.copy(0.3f),
    val bottomAppBarBackgroundColor: Color = Color.Transparent,
    val topAppBarBackgroundColor: Color = Color.Transparent,
    val bottomIconSize: Dp = 35.dp,
    val profileIconTextColor: Color = Color.White,
    val bottomIconColor: Color = Color.Gray,
    )
