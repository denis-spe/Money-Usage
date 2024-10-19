package com.example.moneyusage.frontend.components

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import com.example.moneyusage.R

@Composable
fun AuthTextButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
){
    TextButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.textButtonColors(
            contentColor = colorResource(R.color.authorization_color)
        )
    ) {
        Text(text = text)
    }
}