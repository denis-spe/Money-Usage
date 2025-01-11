package com.example.moneyusage.frontend.screens.home_screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyusage.DEBT
import com.example.moneyusage.EXPENSE
import com.example.moneyusage.INCOME
import com.example.moneyusage.LENT
import com.example.moneyusage.R
import com.example.moneyusage.SAVING
import com.example.moneyusage.backend.models.Data
import com.example.moneyusage.frontend.charts.LineChartComposable
import com.example.moneyusage.frontend.dataclasses.DateTime
import com.example.moneyusage.frontend.helper.WeekDays


@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun TimeLineSection(dataset: State<List<Data>>) {

    val labels = WeekDays.entries.map { it.name }.toMutableList()

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
            fontWeight = FontWeight(750)
        )
    }

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        LineChartComposable(
            data = dataset,
            labels = labels,
        )
    }
}

fun List<Data>.generateData(generate: Int): List<Double> {
    val data = mutableListOf<Data>()
    val weekDays = WeekDays.entries.map { it.name }

    for (i in 0..generate) {
        data.add(
            Data(category = this[0].category, amount = 0.0)
        )
    }

    for (i in indices) {
        val index = weekDays.indexOf(this[i].date?.dayOfTheWeek)
        data[index] = this[i]
    }
    return data.map { it.amount }
}

fun State<List<Data>>.mappingData():
        MutableMap<String, List<Double>> {
    return this.value.groupBy { it.category!! }
        .mapValues { entry ->
            entry.value
                .groupBy { it.date?.dayOfTheWeek }
                .mapValues { innerEntry ->
                    val sum = innerEntry.value.sumOf { data -> data.amount }
                    Data(
                        category = entry.key,
                        amount = sum,
                        date = DateTime(dayOfTheWeek = innerEntry.key)
                    )
                }
                .values
        }
        .mapValues {
            it.value.toMutableList().generateData(6)
        }.toMutableMap()
}

@Composable
fun getColorByName(name: String): Color {
    return when (name) {
        INCOME -> colorResource(id = R.color.income)
        EXPENSE -> colorResource(id = R.color.expense)
        DEBT -> colorResource(id = R.color.debt)
        LENT -> colorResource(id = R.color.lent)
        SAVING -> colorResource(id = R.color.saving)
        else -> Color.Unspecified
    }
}