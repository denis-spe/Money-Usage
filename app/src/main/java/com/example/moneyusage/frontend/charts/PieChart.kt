package com.example.moneyusage.frontend.charts

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyusage.frontend.dataclasses.PieChartData
import com.example.moneyusage.frontend.helper.limitMoneyDigits
import kotlinx.coroutines.launch

data class ArcData(
    val animation: Animatable<Float, AnimationVector1D>,
    val targetSweepAngle: Float,
    val color: Color
)

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun AnimatedPieChart(
    data: List<PieChartData>,
    modifier: Modifier = Modifier,
    size: Dp = 110.dp,
    fontSize: TextUnit = 10.sp
    ) {
    val localModifier = modifier.size(size)
    val total = data.fold(0f) { acc, pieData ->
        acc + pieData.value.toInt()
    }.div(360)
    var currentSum = 0

    val arcs = data.map {
        currentSum += it.value.toInt()
        val target = currentSum / total
        ArcData(
            targetSweepAngle = target,
            animation = Animatable(0f),
            color = it.color
        )
    }

    LaunchedEffect(key1 = arcs) {
        arcs.map {
            launch {
                it.animation.animateTo(
                    targetValue = it.targetSweepAngle.takeIf { it.isFinite() } ?: 0.1f,
                    animationSpec = tween(
                        durationMillis = 4000,
                        easing = FastOutSlowInEasing
                    ),
                    initialVelocity = it.animation.velocity.takeIf { it.isFinite() } ?: 0f
                )
            }
        }
    }

    Box(
        modifier = modifier,
    ) {

        Column(
            modifier = localModifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                data.map {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(2.dp)
                    ) {
                        Text(text = it.label,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = fontSize,
                            color = it.color)

                        Text(text = it.value.toString()
                            .limitMoneyDigits(limitMillonAndHundred = true),
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = fontSize,
                            color = it.color)
                    }
                }
            }
        }

        Canvas(modifier = localModifier) {
            val stroke = Stroke(width = 30f)

            arcs.reversed().map {
                drawArc(
                    startAngle = -90f,
                    sweepAngle = it.animation.value ,
                    color = it.color,
                    useCenter = false,
                    style = stroke
                )
            }
        }
    }
}

