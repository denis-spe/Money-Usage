package com.example.moneyusage.frontend.screens.home_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyusage.DEBT
import com.example.moneyusage.EXPENSE
import com.example.moneyusage.INCOME
import com.example.moneyusage.LENT
import com.example.moneyusage.R
import com.example.moneyusage.SAVING
import com.example.moneyusage.backend.models.Data
import com.example.moneyusage.frontend.charts.AnimatedPieChart
import com.example.moneyusage.frontend.components.convertMillisToDate
import com.example.moneyusage.frontend.dataclasses.PieChartData
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun TodayPeriodPieChart(
    dataset: State<List<Data>>,
) {

    val currentDate = Calendar.getInstance()
    val datetime = currentDate.timeInMillis.convertMillisToDate()

    val filteredDataset = dataset.value.filter {
        data ->
        data.date?.year == datetime.year &&
        data.date?.month == datetime.month &&
        data.date?.day == datetime.day
    }

    val income = filteredDataset.sumOf { if (it.category == INCOME) it.amount else 0.0 }
    val expense = filteredDataset.sumOf { if (it.category == EXPENSE) it.amount else 0.0 }
    val debt = filteredDataset.sumOf { if (it.category == DEBT) it.amount else 0.0 }
    val lent = filteredDataset.sumOf { if (it.category == LENT) it.amount else 0.0 }
    val saving = filteredDataset.sumOf { if (it.category == SAVING) it.amount else 0.0 }

    /**
     * Lists of pie chart data
     */
    val data = listOf(
        PieChartData(
            INCOME, income,
            color = colorResource(id = R.color.income)
        ),
        PieChartData(
            EXPENSE, expense,
            color = colorResource(id = R.color.expense)
        ),
        PieChartData(
            DEBT, debt,
            color = colorResource(id = R.color.debt)
        ),
        PieChartData(
            LENT, lent,
            color = colorResource(id = R.color.lent)
        ),
        PieChartData(
            SAVING, saving,
            color = colorResource(id = R.color.saving)
        ),
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(top = 50.dp)
    ) {
        item {
            SmallPieChart(
                label = INCOME,
                data = data,
                colorId = R.color.income
            )
        }
        item {
            SmallPieChart(
                label = EXPENSE,
                data = data,
                colorId = R.color.expense
            )
        }

        item {
            SmallPieChart(
                label = DEBT,
                data = data,
                colorId = R.color.debt
            )
        }

        item {
            SmallPieChart(
                label = LENT,
                data = data,
                colorId = R.color.lent
            )
        }

        item {
            SmallPieChart(
                label = SAVING,
                data = data,
                colorId = R.color.saving
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun AllPeriodPieChart(
    dataset: State<List<Data>>,
) {
    val income = dataset.value.sumOf { if (it.category == INCOME) it.amount else 0.0 }
    val expense = dataset.value.sumOf { if (it.category == EXPENSE) it.amount else 0.0 }
    val debt = dataset.value.sumOf { if (it.category == DEBT) it.amount else 0.0 }
    val lent = dataset.value.sumOf { if (it.category == LENT) it.amount else 0.0 }
    val saving = dataset.value.sumOf { if (it.category == SAVING) it.amount else 0.0 }



    /**
     * Lists of pie chart data
     */
    val data = listOf(
        PieChartData(
            "Income", income,
            color = colorResource(id = R.color.income)
        ),
        PieChartData(
            "Expense", expense,
            color = colorResource(id = R.color.expense)
        ),

        PieChartData(
            "Debt", debt,
            color = colorResource(id = R.color.debt)
        ),
        PieChartData(
            "Lent", lent,
            color = colorResource(id = R.color.lent)
        ),
        PieChartData(
            "Savings", saving,
            color = colorResource(id = R.color.saving)
        ),
    )

    // Center text
    val centerTextState = remember { mutableStateOf("") }


    Spacer(modifier = Modifier.height(20.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            // Pie Chart
            AnimatedPieChart(
                modifier = Modifier.padding(10.dp),
                data = data,
                strokeWidth = 36f,
                size = 140.dp,
                centerText = centerTextState
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            PieLabel("Income", R.color.income) {
                centerTextState.value = INCOME
            }
            PieLabel("Expense", R.color.expense, "square") {
                centerTextState.value = EXPENSE
            }
            PieLabel("Debt", R.color.debt) {
                centerTextState.value = DEBT
            }
            PieLabel("Lent", R.color.lent, "square") {
                centerTextState.value = LENT
            }
            PieLabel("Savings", R.color.saving) {
                centerTextState.value = SAVING
            }
        }
    }

}

@Composable
fun PieLabel(
    label: String,
    color: Int,
    shape: String = "circle",
    onClick: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(30))
            .clickable { onClick() }
            .padding(start = 5.dp, end = 5.dp)
    ) {
        if (shape == "circle")
            ColorCircle(colorResource(color))
        if (shape == "square")
            ColorBox(colorResource(color))
        Spacer(modifier = Modifier.width(5.dp))
        Text(label, fontSize = 13.sp, modifier = Modifier.padding(end = 4.dp))
    }
}

@Composable
fun ColorCircle(
    color: Color,
    size: Dp = 9.dp,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(100))
            .size(size)
            .background(color)
    )
}

@Composable
fun ColorBox(
    color: Color,
    size: Dp = 9.dp,
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(color)
    )
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun SmallPieChart(
    label: String,
    data: List<PieChartData>,
    colorId: Int
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AnimatedPieChart(
            modifier = Modifier.padding(10.dp),
            data = data
                .sortedBy { if (it.label == label) 0 else 1 }
                .map {
                    if (it.label == label) {
                        if (it.value != 0.0) {
                            it.copy(color = colorResource(id = colorId))
                        } else {
                            it.copy(color = Color.Gray.copy())
                        }
                    } else {
                        it.copy(color = Color.Transparent)
                    }
                },
            strokeWidth = 5f,
            size = 50.dp,
            centerText = null,
            showAmountInCenter = label
        )

        Text(
            label,
            color = colorResource(id = colorId),
            fontSize = 10.sp
        )

    }
}