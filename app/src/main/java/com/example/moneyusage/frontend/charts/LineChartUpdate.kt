// Bless be the name of LORD
package com.example.moneyusage.frontend.charts

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin



// Data class to hold tooltip information.
data class TooltipData(
    val xLabel: String,
    val yValues: List<Float>,
    val position: Offset,  // raw tap position in canvas pixels
    val xPos: Float        // computed x coordinate for the data point (in canvas pixels)
)

data class Line(
    val name: String,
    val points: List<Float>,
    val color: Color
)


@Composable
fun MultiLineChartWithGridAndTooltip(
    lines: List<List<Float>>,        // Each list contains y-values for one line
    xLabels: List<String>? = null,     // Optional x-axis labels
    lineColors: List<Color> = listOf(Color.Blue, Color.Red, Color.Green),
    modifier: Modifier = Modifier,
    chartWidth: Dp = 1000.dp,          // Total canvas width (scrollable)
    chartHeight: Dp = 300.dp,          // Total canvas height
    yAxisTickCount: Int = 5            // Number of tick marks on y-axis
) {
    // Horizontal scrolling state for the entire chart.
    val scrollState = rememberScrollState()

    // State to hold the tooltip data (if any).
    var tooltipData by remember { mutableStateOf<TooltipData?>(null) }

    // We'll need density to convert pixel offsets to dp for overlay positioning.
    val density = LocalDensity.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
    ) {
        // The Canvas with pointerInput to detect tap gestures.
        Canvas(modifier = Modifier
            .size(chartWidth, chartHeight)
            .pointerInput(Unit) {
                detectTapGestures { tapOffset ->
                    // Define margins (same as in drawing below)
                    val marginLeft = 90f   // Left margin for y-axis labels
                    val marginBottom = 60f // Bottom margin for x-axis labels
                    val marginTop = 20f    // Top margin
                    val marginRight = 90f  // Right margin

                    // Plot area is inside margins. Here we add an extra offset from left for the graph.
                    val plotLeft = marginLeft + 40f
                    val plotTop = marginTop
                    val plotWidth = size.width - marginLeft - marginRight
                    val plotHeight = size.height - marginTop - marginBottom

                    // Only consider taps within the plot area.
                    if (tapOffset.x in plotLeft..(plotLeft + plotWidth) &&
                        tapOffset.y in plotTop..(plotTop + plotHeight)
                    ) {
                        // Determine the number of points (assumes all lines have same count).
                        val pointCount = lines.firstOrNull()?.size ?: 0
                        if (pointCount < 2) return@detectTapGestures

                        // Horizontal spacing between points in the plot area.
                        val spacing = plotWidth / (pointCount - 1)
                        // Compute the nearest index based on tap x coordinate.
                        val index = ((tapOffset.x - plotLeft) / spacing).roundToInt().coerceIn(0, pointCount - 1)
                        // Get the x label.
                        val xLabel = xLabels?.getOrNull(index) ?: index.toString()
                        // Compute the exact x coordinate for the data point.
                        val dataXPos = plotLeft + index * spacing
                        // For each line, extract the y value at this index.
                        val yValues = lines.map { it[index] }
                        // Set tooltip data with the tap position and computed x coordinate.
                        tooltipData = TooltipData(xLabel, yValues, tapOffset, dataXPos)
                    } else {
                        tooltipData = null
                    }
                }
            }
        ) {
            // Define margins: left/right margins reserved for labels,
            // bottom for x-axis labels, and top for spacing.
            val marginLeft = 90f   // Left margin for y-axis labels (no y-axis line drawn)
            val marginBottom = 60f // Bottom margin for x-axis labels
            val marginTop = 20f    // Top margin
            val marginRight = 90f  // Right margin

            // Define the plot area inside the margins.
            // Here we add an extra offset from left margin for the actual graph.
            val plotLeft = marginLeft + 40f
            val plotWidth = size.width - marginLeft - marginRight
            val plotHeight = size.height - marginTop - marginBottom

            // Draw the x-axis (horizontal line) outside the plot area.
            drawLine(
                color = Color.Black,
                start = Offset(marginLeft, size.height - marginBottom),
                end = Offset(size.width - marginRight, size.height - marginBottom),
                strokeWidth = 2f
            )

            // Calculate global min and max for y-values (across all lines).
            val allValues = lines.flatten()
            val minY = allValues.minOrNull() ?: 0f
            val maxY = allValues.maxOrNull() ?: 1f

            // Define a dashed path effect for grid lines.
            val dashedEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

            // Draw horizontal dashed grid lines and y-axis tick marks/labels.
            for (i in 0..yAxisTickCount) {
                val fraction = i / yAxisTickCount.toFloat()
                // Compute y position within the plot area (inverted because (0,0) is top-left).
                val yPos = marginTop + (1 - fraction) * plotHeight

                // Draw a horizontal dashed grid line across the plot area.
                drawLine(
                    color = Color.LightGray,
                    start = Offset(marginLeft, yPos),
                    end = Offset(size.width - marginRight, yPos),
                    strokeWidth = 1f,
                    pathEffect = dashedEffect
                )

                // Draw tick mark in the left margin.
                drawLine(
                    color = Color.Black,
                    start = Offset(marginLeft - 5, yPos),
                    end = Offset(marginLeft, yPos),
                    strokeWidth = 2f
                )

                // Compute the actual y value corresponding to this tick.
                val value = minY + fraction * (maxY - minY)
                // Draw y-axis label in the left margin.
                drawContext.canvas.nativeCanvas.drawText(
                    String.format("%.1f", value),
                    5f,
                    yPos + 8f,  // slight offset for baseline
                    android.graphics.Paint().apply {
                        textSize = 28f
                        color = android.graphics.Color.BLACK
                    }
                )
            }

            // Determine the number of data points (assumes all lines have the same count).
            val pointCount = lines.firstOrNull()?.size ?: 0
            if (pointCount < 2) return@Canvas

            // Calculate horizontal spacing between points in the plot area.
            val spacing = plotWidth / (pointCount - 1)

            // Draw x-axis tick marks and labels.
            for (i in 0 until pointCount) {
                val x = plotLeft + i * spacing
                // Draw tick mark on the x-axis.
                drawLine(
                    color = Color.Black,
                    start = Offset(x, size.height - marginBottom),
                    end = Offset(x, size.height - marginBottom + 5),
                    strokeWidth = 2f
                )
                // Determine label: use provided xLabels if available; otherwise, use the index.
                val label = xLabels?.getOrNull(i) ?: "$i"
                drawContext.canvas.nativeCanvas.drawText(
                    label,
                    x - 10,  // adjust for centering
                    size.height - 10,
                    android.graphics.Paint().apply {
                        textSize = 28f
                        color = android.graphics.Color.BLACK
                    }
                )
            }

            // Draw each line within the plot area.
            lines.forEachIndexed { lineIndex, line ->
                val path = Path()
                line.forEachIndexed { index, value ->
                    // Normalize y-value to [0, 1].
                    val normalizedY = (value - minY) / (maxY - minY)
                    // Compute y coordinate (inverted so higher values appear higher).
                    val y = marginTop + (1 - normalizedY) * plotHeight
                    val x = plotLeft + index * spacing
                    if (index == 0) {
                        path.moveTo(x, y)
                    } else {
                        path.lineTo(x, y)
                    }
                }
                drawPath(
                    path = path,
                    color = lineColors.getOrElse(lineIndex) { Color.Black },
                    style = Stroke(width = 4f, cap = StrokeCap.Round)
                )
            }

            // If tooltip is active, draw a vertical dashed line at the data point's x position.
            tooltipData?.let { data ->
                // Ensure the vertical line is drawn only within the plot area.
                if (data.xPos in plotLeft..(plotLeft + plotWidth)) {
                    drawLine(
                        color = Color.Gray,
                        start = Offset(data.xPos, marginTop),
                        end = Offset(data.xPos, marginTop + plotHeight),
                        strokeWidth = 2f,
                        pathEffect = dashedEffect
                    )
                }
            }
        }

        // If tooltip data is available, overlay a tooltip.
        tooltipData?.let { data ->
            // Convert chartWidth dp to pixels.
            val canvasWidthPx = with(density) { chartWidth.toPx() }
            // Define a tooltip width in pixels, e.g., 150 pixels.
            val tooltipWidthPx = 150f
            // Adjust tooltip x position if it's too close to the right edge.
            val adjustedX = if (data.position.x > canvasWidthPx - tooltipWidthPx)
                data.position.x - tooltipWidthPx
            else data.position.x

            // Convert adjusted coordinates to dp.
            val tooltipOffsetX = with(density) { adjustedX.toDp() }
            val tooltipOffsetY = with(density) { data.position.y.toDp() }
            Box(
                modifier = Modifier
                    .offset(x = tooltipOffsetX, y = tooltipOffsetY)
                    .background(Color.White)
            ) {
                // Build tooltip text showing the x label and y values.
                val tooltipText = buildString {
                    append("X: ${data.xLabel}\n")
                    data.yValues.forEachIndexed { index, value ->
                        append("Line ${index + 1}: ${String.format("%.1f", value)}\n")
                    }
                }
                Text(
                    text = tooltipText,
                    color = Color.Black,
                    modifier = Modifier.background(Color.White)
                )
            }
        }
    }
}

@Composable
fun MultiAreaChartWithGridAndTooltip(
    modifier: Modifier = Modifier,
    points: List<Line>,        // Each list contains y-values for one series
    xLabels: List<String>? = null,     // Optional x-axis labels
    chartWidth: Dp = 1000.dp,          // Total canvas width (scrollable)
    chartHeight: Dp = 300.dp,          // Total canvas height
    xAxisLabel: String? = null,
    yAxisTickCount: Int = 5,            // Number of tick marks on y-axis
    tooltipBackground: Color = Color.White,
    axisTextColor: Int = android.graphics.Color.BLACK,
    horizontalLineColor: Color = Color.Gray,
    tooltipVerticalLineColor: Color = Color.Gray,
    axisTextSize: Float = 28f,
    lineEffect: String = "dashed",
    tooltipTextColor: Color = Color.Gray,
    tooltipTextWeight: FontWeight = FontWeight.Bold,
    tooltipTextSize: TextUnit = 13.sp,
    fillArea: Boolean = true,
    gridColor: Color = Color.LightGray,
    @SuppressLint("DefaultLocale") mapYAxis: (Float) -> String = { String.format("%.1f", it) },
    mapTooltip: (index: Int, value: Float) -> String = { index, value ->
        val names = points.map { it.name }
        String.format("${names[index]}: %.1f", value) },

    ) {
    // Data points
    val lines = points.map { it.points }
    val lineColors = points.map { it.color }

    // Horizontal scrolling state for the entire chart.
    val scrollState = rememberScrollState()

    // State to hold the tooltip data (if any).
    var tooltipData by remember { mutableStateOf<TooltipData?>(null) }

    // We'll need density to convert pixel offsets to dp for overlay positioning.
    val density = LocalDensity.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
    ) {
        // The Canvas with pointerInput to detect tap gestures.
        Canvas(modifier = Modifier
            .size(chartWidth, chartHeight)
            .pointerInput(Unit) {
                detectTapGestures { tapOffset ->
                    // Define margins (same as in drawing below)
                    val marginLeft = 90f   // Left margin for y-axis labels
                    val marginBottom = 60f // Bottom margin for x-axis labels
                    val marginTop = 20f    // Top margin
                    val marginRight = 90f  // Right margin

                    // Plot area is inside margins. Here we add an extra offset from left for the graph.
                    val plotLeft = marginLeft + 40f
                    val plotWidth = size.width - marginLeft - marginRight
                    val plotHeight = size.height - marginTop - marginBottom

                    // Only consider taps within the plot area.
                    if (tapOffset.x in plotLeft..(plotLeft + plotWidth) &&
                        tapOffset.y in marginTop..(marginTop + plotHeight)
                    ) {
                        // Determine the number of points (assumes all lines have same count).
                        val pointCount = lines.firstOrNull()?.size ?: 0
                        if (pointCount < 2) return@detectTapGestures

                        // Horizontal spacing between points in the plot area.
                        val spacing = plotWidth / (pointCount - 1)
                        // Compute the nearest index based on tap x coordinate.
                        val index = ((tapOffset.x - plotLeft) / spacing).roundToInt().coerceIn(0, pointCount - 1)
                        // Get the x label.
                        val xLabel = xLabels?.getOrNull(index) ?: index.toString()
                        // Compute the exact x coordinate for the data point.
                        val dataXPos = plotLeft + index * spacing
                        // For each line, extract the y value at this index.
                        val yValues = lines.map { it[index] }
                        // Set tooltip data with the tap position and computed x coordinate.
                        tooltipData = TooltipData(xLabel, yValues, tapOffset, dataXPos)
                    } else {
                        tooltipData = null
                    }
                }
            }
        ) {
            // Define margins: left/right margins reserved for labels,
            // bottom for x-axis labels, and top for spacing.
            val marginLeft = 90f   // Left margin for y-axis labels (no y-axis line drawn)
            val marginBottom = 60f // Bottom margin for x-axis labels
            val marginTop = 20f    // Top margin
            val marginRight = 90f  // Right margin

            // Define the plot area inside the margins.
            // Here we add an extra offset from left margin for the actual graph.
            val plotLeft = marginLeft + 40f
            val plotWidth = size.width - marginLeft - marginRight
            val plotHeight = size.height - marginTop - marginBottom

            // Draw the x-axis (horizontal line) outside the plot area.
            drawLine(
                color = horizontalLineColor,
                start = Offset(marginLeft, size.height - marginBottom),
                end = Offset(size.width - marginRight, size.height - marginBottom),
                strokeWidth = 3f
            )

            // Calculate global min and max for y-values (across all lines).
            val allValues = lines.flatten()
            val minY = allValues.minOrNull() ?: 0f
            val maxY = allValues.maxOrNull() ?: 1f

            var dashedEffect: PathEffect? = null

            // Define a dashed path effect for grid lines and vertical touch line.
            if (lineEffect == "solid"){
                dashedEffect = PathEffect.dashPathEffect(floatArrayOf(0f, 0f), 0f)
            }
            else if (lineEffect == "dashed"){
                dashedEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            }
            else if (lineEffect == "dotted"){
                dashedEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 5f), 0f)
            }
            else if (lineEffect == "dot-dashed"){
                dashedEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 5f, 10f, 5f), 0f)
            }
            else if (lineEffect == "dash-dot"){
                dashedEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f, 5f, 5f), 0f)
            }
            else if (lineEffect == "long-dash"){
                dashedEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f), 0f)
            }
            else if (lineEffect == "long-dash-dot"){
                dashedEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f, 5f, 5f), 0f)
            }
            else {
                dashedEffect = PathEffect.dashPathEffect(floatArrayOf(0f, 0f), 0f)
            }

            // Draw horizontal dashed grid lines and y-axis tick marks/labels.
            for (i in 0..yAxisTickCount) {
                val fraction = i / yAxisTickCount.toFloat()
                // Compute y position within the plot area (inverted because (0,0) is top-left).
                val yPos = marginTop + (1 - fraction) * plotHeight

                // Draw a horizontal dashed grid line across the plot area.
                drawLine(
                    color = gridColor,
                    start = Offset(marginLeft, yPos),
                    end = Offset(size.width - marginRight, yPos),
                    strokeWidth = 1f,
                    pathEffect = dashedEffect
                )

                // Draw tick mark in the left margin.
                drawLine(
                    color = gridColor,
                    start = Offset(marginLeft - 5, yPos),
                    end = Offset(marginLeft, yPos),
                    strokeWidth = 2f
                )

                // Compute the actual y value corresponding to this tick.
                val value = minY + fraction * (maxY - minY)
                // Draw y-axis label in the left margin.
                drawContext.canvas.nativeCanvas.drawText(
                    mapYAxis(value),
                    5f,
                    yPos + 8f,  // slight offset for baseline
                    android.graphics.Paint().apply {
                        textSize = axisTextSize
                        color = axisTextColor
                    }
                )
            }

            // Determine the number of data points (assumes all lines have the same count).
            val pointCount = lines.firstOrNull()?.size ?: 0
            if (pointCount < 2) return@Canvas

            // Calculate horizontal spacing between points in the plot area.
            val spacing = plotWidth / (pointCount - 1)

            // Draw x-axis tick marks and labels.
            for (i in 0 until pointCount) {
                val x = plotLeft + i * spacing
                // Draw tick mark on the x-axis.
                drawLine(
                    color = horizontalLineColor,
                    start = Offset(x, size.height - marginBottom),
                    end = Offset(x, size.height - marginBottom + 5),
                    strokeWidth = 2f
                )
                // Determine label: use provided xLabels if available; otherwise, use the index.
                val label = xLabels?.getOrNull(i) ?: "$i"
                drawContext.canvas.nativeCanvas.drawText(
                    label,
                    x - 10,  // adjust for centering
                    size.height - 10,
                    android.graphics.Paint().apply {
                        textSize = axisTextSize
                        color = axisTextColor
                    }
                )
            }

            // Draw each series as an area chart.
            lines.forEachIndexed { lineIndex, line ->
                // Build the area path.
                val areaPath = Path().apply {
                    // Start at the first data point.
                    if (line.isNotEmpty()) {
                        val firstY =
                            marginTop + (1 - ((line.first() - minY) / (maxY - minY))) * plotHeight
                        moveTo(plotLeft, firstY)
                    }
                    line.forEachIndexed { index, value ->
                        val normalizedY = (value - minY) / (maxY - minY)
                        val y = marginTop + (1 - normalizedY) * plotHeight
                        val x = plotLeft + index * spacing
                        lineTo(x, y)
                    }
                    // At the end, draw a line to the bottom right of the plot area.
                    lineTo(plotLeft + (line.size - 1) * spacing, marginTop + plotHeight)
                    // Then line back to the bottom left.
                    lineTo(plotLeft, marginTop + plotHeight)
                    close()
                }

                // Fill the area with a semi-transparent version of the line color.
                if (fillArea) {
                    drawPath(
                        path = areaPath,
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                lineColors.getOrElse(lineIndex) { Color.Black }.copy(alpha = 0.3f),
                                Color.Transparent
                            )
                        ),
                        style = Fill
                    )
                }

                // Build the line path (to be drawn on top).
                val linePath = Path().apply {
                    line.forEachIndexed { index, value ->
                        val normalizedY = (value - minY) / (maxY - minY)
                        val y = marginTop + (1 - normalizedY) * plotHeight
                        val x = plotLeft + index * spacing
                        if (index == 0) moveTo(x, y) else lineTo(x, y)
                    }
                }
                // Draw the line.
                drawPath(
                    path = linePath,
                    color = lineColors.getOrElse(lineIndex) { Color.Black },
                    style = Stroke(width = 2f, cap = StrokeCap.Round)
                )
            }

            // If tooltip is active, draw a vertical dashed line at the data point's x position.
            tooltipData?.let { data ->
                // Ensure the vertical line is drawn only within the plot area.
                if (data.xPos in plotLeft..(plotLeft + plotWidth)) {
                    drawLine(
                        color = tooltipVerticalLineColor,
                        start = Offset(data.xPos, marginTop),
                        end = Offset(data.xPos, marginTop + plotHeight),
                        strokeWidth = 2f,
                        pathEffect = dashedEffect
                    )
                }
            }
        }

        // If tooltip data is available, overlay a tooltip.
        tooltipData?.let { data ->
            // Convert chartWidth dp to pixels.
            val canvasWidthPx = with(density) { chartWidth.toPx() }
            // Define a tooltip width in pixels, e.g., 150 pixels.
            val tooltipWidthPx = 150f
            // Adjust tooltip x position if it's too close to the right edge.
            val adjustedX = if (data.position.x > canvasWidthPx - tooltipWidthPx)
                data.position.x - tooltipWidthPx
            else data.position.x

            // Convert adjusted coordinates to dp.
            val tooltipOffsetX = with(density) { adjustedX.toDp() }
            val tooltipOffsetY = with(density) { data.position.y.toDp() }
            Box(
                modifier = Modifier
                    .offset(x = tooltipOffsetX, y = tooltipOffsetY)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(tooltipBackground)
            ) {
                val x = xAxisLabel ?: "X"

                // Build tooltip text showing the x label and y values.
                val tooltipText = buildString {
                    append("${x}: ${data.xLabel}\n")
                    data.yValues.forEachIndexed { index, value ->
                        append("${mapTooltip(index, value)}\n")
                    }
                }
                Column(
                    modifier = Modifier.padding(8.dp).background(tooltipBackground),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = tooltipText,
                        color = tooltipTextColor,
                        fontWeight = tooltipTextWeight,
                        fontSize = tooltipTextSize,
                    )
                }
            }
        }
    }
}




@Preview(showBackground = true, widthDp = 400, heightDp = 350)
@Composable
fun MultiLineChartPreview() {
    // Example sample data with three lines
    val line1 = List(50) { (sin(it / 5.0) * 50 + 100).toFloat() }
    val line2 = List(50) { (cos(it / 5.0) * 50 + 100).toFloat() }
    val line3 = List(50) { (sin(it / 3.0) * 30 + 120).toFloat() }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MultiAreaChartWithGridAndTooltip(
            points = listOf(
                Line(
                    name = "line 1",
                    points = line1,
                    color = Color.Blue
                ),
                Line(
                    name = "line 2",
                    points = line2,
                    color = Color.Red
                ),
                Line(
                    name = "line 3",
                    points = line3,
                    color = Color.Green
                )
            ),
            chartWidth = 1000.dp,
            chartHeight = 300.dp,
            modifier = Modifier
        )
    }
}
