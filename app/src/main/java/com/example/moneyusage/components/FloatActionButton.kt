package com.example.moneyusage.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.unit.dp
import com.example.moneyusage.dataclasses.FloatActionButtonData

@Composable
fun FloatActionButton(
    floatActionButtonData: FloatActionButtonData,
    appearanceState: MutableState<String>,
) {
    val state = floatActionButtonData.label == appearanceState.value
    val containerColor = if (state) floatActionButtonData.containerCloseColor
    else floatActionButtonData.containerOpenColor
    val contentColor = if (state) floatActionButtonData.contentCloseColor
    else floatActionButtonData.contentOpenColor
    val icon = if (state) Icons.Filled.Close
    else Icons.Filled.Add

    FloatingActionButton(
        onClick = {
            floatActionButtonData.onClick()
            appearanceState.value = floatActionButtonData.label
        },
        containerColor = containerColor,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(10.dp),
        shape = CircleShape,
        contentColor = contentColor
    ) {
        Icon(
            icon,
            contentDescription = floatActionButtonData.label
        )
    }

    // Handle popup for floating action button

}