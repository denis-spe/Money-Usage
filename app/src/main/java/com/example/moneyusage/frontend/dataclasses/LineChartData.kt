package com.example.moneyusage.frontend.dataclasses

import androidx.compose.ui.graphics.Color

data class LineChartData(
    val name: String,
    val values: List<Double>,
    val color: Color?,
)
