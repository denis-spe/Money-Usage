package com.example.moneyusage.charts

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.collection.emptyLongSet
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.PlotType
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
fun LineChartComponent(
    data: List<Point>,
    modifier: Modifier = Modifier,
    steps: Int = 5,
    labelData: (Int) -> String = { "" },
    mapPopUpLabel: (Float, Float) -> String = { _, _ -> "" }
){

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.White)
        .steps(data.size - 1)
        .labelData {
            labelData(it)
        }
        .labelAndAxisLinePadding(15.dp)
        .axisLineColor(lineColor = Color.LightGray)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.White)
        .labelAndAxisLinePadding(20.dp)
        .axisLineColor(Color.White)
        .labelData { i ->
            val yScale = 100 / steps
            (i * yScale).toString()
        }.build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = data,
                    LineStyle(
                        lineType = LineType.SmoothCurve(isDotted = false),
                        width = 5f,
                        color = Color.Red
                    ),
                    IntersectionPoint(radius = 0.dp),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0.5445f, 0.5439f, 0.324f),
                                Color(0.86363f, 0.6653f, 0.5362f)),
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
        modifier = modifier.fillMaxWidth()
            .height(300.dp),
        lineChartData = lineChartData
    )
}

