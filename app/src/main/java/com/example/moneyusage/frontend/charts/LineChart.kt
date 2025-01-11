package com.example.moneyusage.frontend.charts

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.moneyusage.backend.models.Data
import com.example.moneyusage.frontend.dataclasses.LineChartData
import com.example.moneyusage.frontend.screens.home_screen.getColorByName
import com.example.moneyusage.frontend.screens.home_screen.mappingData
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.extensions.format
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DotProperties
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.GridProperties
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.LabelProperties
import ir.ehsannarmani.compose_charts.models.Line
import ir.ehsannarmani.compose_charts.models.PopupProperties
import ir.ehsannarmani.compose_charts.models.StrokeStyle


@Composable
fun LineChartComposable(data: State<List<Data>>, labels: List<String>) {
    val isDark = isSystemInDarkTheme()
    val labelsColor = if (isDark) Color.White else Color.Black

    val mappedData = if (data.value.isNotEmpty()) data.mappingData().map {
        LineChartData(
            name = it.key,
            values = it.value,
            color = getColorByName(it.key)
        )
    } else listOf(
        LineChartData(
            name = "",
            values = listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
            color = Color.Unspecified
        )
    )

    val dataset = mappedData.map {
        Line(
            label = it.name,
            values = it.values,
            color = SolidColor(it.color!!),
            firstGradientFillColor = it.color.copy(alpha = .5f),
            secondGradientFillColor = if (it.name.isNotEmpty()) Color.Transparent else Color.Unspecified,
            strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
            gradientAnimationDelay = 3000,
            drawStyle = DrawStyle.Stroke(width = 2.dp),
            dotProperties = DotProperties(
                enabled = true,
                color = SolidColor(it.color)
            ),
            curvedEdges = true,
            popupProperties = PopupProperties(
                containerColor = it.color.copy(alpha = .5f),
                mode = PopupProperties.Mode.PointMode(),
                textStyle = TextStyle.Default.copy(
                    color = labelsColor,
                ),
            ) { value ->
                "R${value.format(2)}"
            }
        )
    }

    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        data = dataset,
        labelProperties = LabelProperties(
            enabled = true,
            labels = labels,
            textStyle = TextStyle.Default.copy(
                color = labelsColor,
            )
        ),
        indicatorProperties = HorizontalIndicatorProperties(
            textStyle = TextStyle.Default.copy(
                color = labelsColor,
            )
        ),
        labelHelperProperties = LabelHelperProperties(
            enabled = true,
            textStyle = TextStyle.Default.copy(
                color = labelsColor,
            )
        ),
        gridProperties = GridProperties(
            enabled = true,
            xAxisProperties = GridProperties.AxisProperties(
                color = SolidColor(Color.LightGray),
                thickness = (.3).dp,
                style = StrokeStyle.Dashed()
            ),
            yAxisProperties = GridProperties.AxisProperties(
                enabled = false,
            )
        ),
        animationMode = AnimationMode.Together(delayBuilder = {
            it * 500L
        }),
    )
}

