package com.example.moneyusage.frontend.screens.home_screen

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.moneyusage.R


@Composable
fun FloatActionButton(
    onOpenDialogState: MutableState<Boolean>
) {

    val primaryThemeColor = colorResource(id = R.color.primaryThemeColor)
    val secondaryThemeColor = colorResource(id = R.color.secondaryThemeColor)

    FloatingActionButton(
        onClick = {
            onOpenDialogState.value = true
        },
        containerColor = if (onOpenDialogState.value) secondaryThemeColor
        else primaryThemeColor,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(10.dp),
        shape = CircleShape,
    ) {
        Icon(
            imageVector = if (onOpenDialogState.value) Icons.Default.Close
            else Icons.Default.Add,
            tint = Color.White,
            contentDescription = "Add")
    }
}