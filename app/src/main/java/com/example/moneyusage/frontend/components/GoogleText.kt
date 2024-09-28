package com.example.moneyusage.frontend.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GoogleText(){
    Text(
        buildAnnotatedString {
//            withStyle(style = SpanStyle(
//                color = Color(1, 142, 255, 255),
//                letterSpacing = 0.7.sp
//            )
//            ) {
//                append("G")
//            }

            withStyle(style = SpanStyle(
                color = Color(214, 43, 8, 255),
                letterSpacing = 0.7.sp
            )
            ) {
                append("o")
            }

            withStyle(style = SpanStyle(
                color = Color(238, 164, 86, 255),
                letterSpacing = 0.7.sp
            )
            ) {
                append("o")
            }

            withStyle(style = SpanStyle(
                color = Color(1, 142, 255, 255),
                letterSpacing = 0.7.sp
            )
            ) {
                append("g")
            }

            withStyle(style = SpanStyle(
                color = Color(22, 133, 33, 255),
                letterSpacing = 0.7.sp
            )
            ) {
                append("l")
            }

            withStyle(style = SpanStyle(
                color = Color(214, 43, 8, 255),
                letterSpacing = 0.7.sp
            )
            ) {
                append("e")
            }
        },

        fontSize = 17.sp
    )
}