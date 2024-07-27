package com.example.moneyusage.charts

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine

@SuppressLint("DefaultLocale")
@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    data: List<Point>,
    secondData: List<Point> = emptyList(),
    colors: List<Color> = listOf(Color.Red, Color.Green),
    steps: Int = 5,
    labelData: (Int) -> String = { "" },
    mapPopUpLabel: (Float, Float) -> String = { x, y ->
        val xLabel = "x : ${x.toInt()} "
        val yLabel = "y : ${String.format("%.2f", y)}"
        "$xLabel $yLabel"
    }
) {

    val labelColor = if (isSystemInDarkTheme()) Color.White
    else Color.Gray

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .steps(data.size - 1)
        .axisLabelColor(labelColor)
        .labelData {
            labelData(it)
        }
        .labelAndAxisLinePadding(15.dp)
        .axisLineColor(lineColor = Color.LightGray)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .axisLabelColor(labelColor)
        .labelAndAxisLinePadding(20.dp)
        .axisLineColor(Color.Unspecified)
        .labelData { i ->
            val yScale = 100 / steps
            (i * yScale).toString()
        }.build()

    val intervals = floatArrayOf(10f, 5f)

    val radius = 5.dp

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = data,
                    LineStyle(
                        lineType = LineType.SmoothCurve(
                            isDotted = false,
                            intervals = intervals
                        ),
                        width = 5f,
                        color = colors[0]
                    ),
                    IntersectionPoint(radius = 0.dp),
                    SelectionHighlightPoint(
                        color = colors[0],
                        radius = radius
                    ),
                    ShadowUnderLine(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colors[0].copy(alpha = 0.556f),
                                colors[0].copy(alpha = 0.256f)
                            ),
                            start = Offset.Zero,
                            end = Offset.Infinite
                        )
                    ),
                    SelectionHighlightPopUp(
                        backgroundColor = Color.LightGray,
                        backgroundAlpha = 0.4f,
                        labelSize = 18.sp,
                        popUpLabel = { x, y ->
                            if (x.toInt() == 0 && y.toInt() == 0)
                                "";
                            else
                                mapPopUpLabel(x, y);
                        },
                    )
                ),

                Line(
                    dataPoints = secondData,
                    LineStyle(
                        lineType = LineType.SmoothCurve(
                            isDotted = false,
                            intervals = intervals
                            ),
                        width = 5f,
                        color = colors[1]
                    ),
                    IntersectionPoint(radius = 0.dp),
                    SelectionHighlightPoint(
                        color = colors[1],
                        radius = radius
                    ),
                    ShadowUnderLine(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colors[1].copy(alpha = 0.556f),
                                colors[1].copy(alpha = 0.256f)
                            ),
                            start = Offset.Zero,
                            end = Offset.Infinite
                        )
                    ),
                    SelectionHighlightPopUp(
                        backgroundColor = Color.LightGray,
                        backgroundAlpha = 0.4f,
                        labelSize = 18.sp,
                        popUpLabel = { x, y ->
                            if (x.toInt() == 0 && y.toInt() == 0)
                                "";
                            else
                                mapPopUpLabel(x, y);
                        },
                    )
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(
            enableVerticalLines = false,
            lineWidth = 0.5.dp,
            alpha = 0.8f
        ),
        backgroundColor = Color.Transparent,
        paddingTop = 40.dp,
    )

    LineChart(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = lineChartData
    )
}

