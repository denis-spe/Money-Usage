package com.example.moneyusage.frontend.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun AuthOutlineButton(
    label: String,
    onClick: () -> Unit
){
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(2.dp)
    ) {
        Text(text = label)
    }
}