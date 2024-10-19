package com.example.moneyusage.frontend.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.moneyusage.R

@Composable
fun AuthOutlineButton(
    label: String,
    onClick: () -> Unit
){
    val colorResource = colorResource(id = R.color.authorization_color)

    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(2.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = colorResource
        ),
        border = BorderStroke(1.dp, colorResource)
    ) {
        Text(text = label)
    }
}