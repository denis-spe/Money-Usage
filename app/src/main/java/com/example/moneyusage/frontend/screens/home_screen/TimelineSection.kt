package com.example.moneyusage.frontend.screens.home_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.Point
import com.example.moneyusage.frontend.charts.LineChart
import com.example.moneyusage.frontend.dataclasses.PieChartData
import com.example.moneyusage.frontend.helper.WeekDays.Fri
import com.example.moneyusage.frontend.helper.WeekDays.Mon
import com.example.moneyusage.frontend.helper.WeekDays.Sat
import com.example.moneyusage.frontend.helper.WeekDays.Sun
import com.example.moneyusage.frontend.helper.WeekDays.Thu
import com.example.moneyusage.frontend.helper.WeekDays.Tue
import com.example.moneyusage.frontend.helper.WeekDays.Wed

@RequiresApi(Build.VERSION_CODES.P)
fun timeLineSection(
): LazyListScope.() -> Unit {
    return {
        // Line chart Item
        item {
            Spacer(modifier = Modifier.height(20.dp))

            // Sub Title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                Text(
                    "Timeline chart",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(450)
                )
            }

            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                val pointsData: List<Point> =
                    listOf(
                        Point(0f, 0f),
                        Point(1f, 90f),
                        Point(2f, 80f),
                        Point(3f, 80f),
                        Point(4f, 30f),
                        Point(5f, 23f),
                        Point(6f, 53f),
                        Point(7f, 69f),
                    )

                val data: List<Point> =
                    listOf(
                        Point(0f, 0f),
                        Point(1f, 32f),
                        Point(2f, 56f),
                        Point(3f, 87f),
                        Point(4f, 25f),
                        Point(5f, 23f),
                        Point(6f, 73f),
                        Point(7f, 49f),
                    )

                LineChart(
                    data = pointsData,
                    secondData = data,
                    labelData = {
                        when (it) {
                            0 -> ""
                            3 -> Mon.name
                            4 -> Tue.name
                            5 -> Wed.name
                            else -> ""
                        }
                    },

                    mapPopUpLabel = { x, y ->
                        val xLabel = when (x.toInt()) {
                            1 -> Mon
                            2 -> Tue
                            3 -> Wed
                            4 -> Thu
                            5 -> Fri
                            6 -> Sat
                            7 -> Sun
                            else -> ""
                        }
                        "$xLabel : $y"
                    },
                )
            }
        }
    }
}