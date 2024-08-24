package com.example.moneyusage.dataclasses

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class FloatActionButtonData(
    val containerOpenColor: Color,
    val containerCloseColor: Color,
    val openIcon: Int,
    val closeIcon: Int,
    val contentOpenColor: Color = Color.White,
    val contentCloseColor: Color = Color.White,
    val label: String,
    val onClick: () -> Unit
)
