package com.example.moneyusage.frontend.screens.home_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.moneyusage.R


@Composable
fun FloatActionButton(
    onOpenDialogState: MutableState<Boolean>
) {

    val primaryThemeColor = colorResource(id = R.color.primaryThemeColor)

    var rotation by remember { mutableFloatStateOf(0f) }

    // Animate rotation when the value of `rotation` changes
    val animatedRotation by animateFloatAsState(
        targetValue = rotation,
        animationSpec = tween(durationMillis = 1000),
        label = "",
    )

    FloatingActionButton(
        onClick = {
            rotation += 360f
            onOpenDialogState.value = true
        },
        containerColor = primaryThemeColor,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(10.dp),
        shape = CircleShape,
    ) {
        Icon(
            modifier = Modifier.size(25.dp)
                .graphicsLayer(rotationZ = animatedRotation),
            imageVector = if (onOpenDialogState.value) Icons.Default.Close
            else Icons.Default.Add,
            tint = Color.White,
            contentDescription = "Add")
    }
}