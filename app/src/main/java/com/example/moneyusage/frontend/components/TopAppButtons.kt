package com.example.moneyusage.frontend.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyusage.R
import com.example.moneyusage.frontend.dataclasses.TopAppButtonArgs
import com.example.moneyusage.frontend.dataclasses.TopAppButtonColors

@Composable
fun TopAppButton(
    topAppButtonArgs: TopAppButtonArgs,
    selectedButtonBackground: (label: String) -> TopAppButtonColors
){
    val interactiveClickColor = colorResource(R.color.primaryThemeColor)
    val textColor = selectedButtonBackground(topAppButtonArgs.label).textColor

    Button(
        onClick = {
            topAppButtonArgs.selectedButton.value = topAppButtonArgs.label
            topAppButtonArgs.onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = selectedButtonBackground(topAppButtonArgs.label).background,
        ),
        border = BorderStroke(1.dp, interactiveClickColor)
    ) {
        Text(
            topAppButtonArgs.label,
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold,
            color = textColor
        )
    }
}