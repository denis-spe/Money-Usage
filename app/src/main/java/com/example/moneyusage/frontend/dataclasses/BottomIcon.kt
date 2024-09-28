package com.example.moneyusage.frontend.dataclasses

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp

data class BottomIcon(
    val outlineIcon: Painter,
    val filledIcon: Painter,
    val description: String,
    val size: Dp = Styles().bottomIconSize,
    val state: State<Boolean>,
    val animateDelayMillis: Int,
    val onClick: () -> Unit
)
