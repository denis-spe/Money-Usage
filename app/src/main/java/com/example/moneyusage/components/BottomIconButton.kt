package com.example.moneyusage.components

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
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.moneyusage.dataclasses.BottomIcon
import com.example.moneyusage.dataclasses.Styles

@Composable
fun BottomIconButton(
    buttonIcon: BottomIcon,
){
    val styles = Styles()

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
            IconButton(onClick = buttonIcon.onClick) {
                Icon(
                    painter = buttonIcon.outlineIcon,
                    contentDescription = buttonIcon.description,
                    modifier = Modifier.size(buttonIcon.size),
                    tint = styles.bottomIconColor
                )
            }
        }
    }
}