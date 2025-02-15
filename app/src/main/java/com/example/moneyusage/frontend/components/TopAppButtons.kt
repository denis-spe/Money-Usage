package com.example.moneyusage.frontend.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyusage.R
import com.example.moneyusage.frontend.dataclasses.TopAppButtonArgs
import com.example.moneyusage.frontend.dataclasses.TopAppButtonColors
import com.example.moneyusage.frontend.helper.Period

@Composable
fun TopAppButton(
    topAppButtonArgs: TopAppButtonArgs,
    selectedButtonBackground: (label: Period) -> TopAppButtonColors
){
    val interactiveClickColor = colorResource(R.color.primaryThemeColor)
    val textColor = selectedButtonBackground(topAppButtonArgs.label).textColor

    TextButton (
        onClick = {
            topAppButtonArgs.selectedButton.value = topAppButtonArgs.label
            topAppButtonArgs.onClick()
        },
        border = BorderStroke(1.dp, interactiveClickColor),
        modifier = Modifier.padding(end = 10.dp)
    ) {
        Text(
            topAppButtonArgs.label.name,
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraBold,
            color = textColor
        )
    }
}