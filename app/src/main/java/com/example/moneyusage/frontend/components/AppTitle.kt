package com.example.moneyusage.frontend.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun AppTitle(
    fontSize: TextUnit = 20.sp
){
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(
                color = Color(56, 170, 71, 174),
                fontWeight = FontWeight.Bold,
                fontSize = fontSize,
                letterSpacing = 0.7.sp
            )) {
                append("Money")
            }

            append(" ")

            withStyle(style = SpanStyle(
                color = Color(214, 43, 8, 255),
                fontWeight = FontWeight.Bold,
                fontSize = fontSize,
                letterSpacing = 0.7.sp
                )) {
                append("usage")
            }
        }
    )
}