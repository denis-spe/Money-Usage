package com.example.moneyusage.frontend.screens.home_screen

import android.os.Build
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
import com.example.moneyusage.backend.models.Data
import com.example.moneyusage.frontend.charts.AnimatedPieChart
import com.example.moneyusage.frontend.dataclasses.PieChartData

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PieChartSection(
    dataset: State<List<Data>>,
) {
    val income = dataset.value.sumOf { if (it.dataName == INCOME) it.amount else 0.0 }
    val expense = dataset.value.sumOf { if (it.dataName == EXPENSE) it.amount else 0.0 }
    val debt = dataset.value.sumOf { if (it.dataName == DEBT) it.amount else 0.0 }
    val lent = dataset.value.sumOf { if (it.dataName == LENT) it.amount else 0.0 }

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

