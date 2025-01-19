package com.example.moneyusage.frontend.charts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
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
    size: Dp = 100.dp,
    centerText: MutableState<String>? = null,
    strokeWidth: Float = 30f,
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
            val maxData = data.first { first -> first.value == data.maxOf { it.value } }
            val minData = data.first { first -> first.value == data.minOf { it.value } }

            if (centerText != null && centerText.value.isEmpty()) {
                Text(
                    "Max: " +
                            maxData.value.toString().limitMoneyDigits(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = maxData.color
                )

                Text(
                    "Min: " +
                            minData.value.toString().limitMoneyDigits(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    color = minData.color
                )
            }
            else {
                val selectData = data.first { first -> first.label == centerText?.value }
                Text(
                    "${selectData.label}: ${selectData.value.toString().limitMoneyDigits()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = selectData.color
                )

                Text("max-min",
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {  centerText?.value = ""  }
                        .padding(start = 5.dp, end = 5.dp),
                    fontSize = 10.sp
                )

            }
        }

        Canvas(modifier = localModifier) {
            val stroke = Stroke(width = strokeWidth)

            arcs.reversed().map {
                drawArc(
                    startAngle = -90f,
                    sweepAngle = it.animation.value,
                    color = it.color,
                    useCenter = false,
                    style = stroke
                )
            }
        }
    }
}

