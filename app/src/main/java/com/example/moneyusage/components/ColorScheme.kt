package com.example.moneyusage.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ColorScheme(isDarkTheme: Boolean): List<Color> {
    val lightColors = listOf(
        Color(0xFF6200EE), // Primary
        Color(0xFFFFFFFF), // Background
        Color(0xFF000000)  // Text color
        // ... other light colors
    )

    val darkColors = listOf(
        Color(0xFFBB86FC), // Primary
        Color(0xFF121212), // Background
        Color(0xFFFFFFFF)  // Text color
        // ... other dark colors
    )

    val colors = if (isDarkTheme) darkColors else lightColors
    return colors
}