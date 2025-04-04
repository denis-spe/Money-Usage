package com.example.moneyusage.frontend.screens.home_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.moneyusage.R
import com.example.moneyusage.frontend.dataclasses.BottomIcon

@Composable
fun BottomIconButton(
    buttonIcon: BottomIcon,
    selectState: MutableState<String>,
){
    val interactiveClickColor = colorResource(id = R.color.primaryThemeColor)

    val icon = when(selectState.value){
        buttonIcon.description -> buttonIcon.filledIcon
        else -> buttonIcon.outlineIcon
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = buttonIcon.state.value,
            enter = slideInVertically{
                it
            } + fadeIn(
                tween(
                durationMillis = buttonIcon.animateDelayMillis,
                easing = EaseIn
            )
            ) + expandVertically { it },
            exit = slideOutVertically {
                it
            } + fadeOut(
                tween(
                durationMillis = buttonIcon.animateDelayMillis,
                easing = EaseOut
            )
            ) + shrinkVertically { it }
        ) {
            IconButton(
                onClick = {
                    selectState.value = buttonIcon.description
                    buttonIcon.onClick()
                },
            ) {
                Icon(
                    painter = icon,
                    contentDescription = buttonIcon.description,
                    modifier = Modifier.size(buttonIcon.size),
                    tint = interactiveClickColor
                )
            }
        }
    }
}