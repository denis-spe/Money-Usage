package com.example.moneyusage.frontend.components

import android.R.color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyusage.frontend.dataclasses.FloatActionButtonData


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
    val icon = if (state) painterResource(id = floatActionButtonData.closeIcon)
    else painterResource(id = floatActionButtonData.openIcon)
    val text = if (floatActionButtonData.label != "Add") floatActionButtonData.label
    else ""
    val offset = Offset(2.0f, 6.0f)

    Row(
        modifier = Modifier
            .width(210.dp)
            .height(60.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            style = TextStyle(shadow = Shadow(color= containerColor, offset = offset, blurRadius = 10f))
        )
        Spacer(modifier = Modifier.width(10.dp))

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

    }
}