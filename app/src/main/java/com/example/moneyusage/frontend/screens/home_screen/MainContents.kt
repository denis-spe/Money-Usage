package com.example.moneyusage.frontend.screens.home_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.Point
import com.example.moneyusage.DEBT
import com.example.moneyusage.EXPENSE
import com.example.moneyusage.INCOME
import com.example.moneyusage.LENT
import com.example.moneyusage.R
import com.example.moneyusage.frontend.charts.LineChart
import com.example.moneyusage.frontend.components.RecentTransactions
import com.example.moneyusage.frontend.dataclasses.PieChartData
import com.example.moneyusage.frontend.helper.WeekDays.Fri
import com.example.moneyusage.frontend.helper.WeekDays.Mon
import com.example.moneyusage.frontend.helper.WeekDays.Sat
import com.example.moneyusage.frontend.helper.WeekDays.Sun
import com.example.moneyusage.frontend.helper.WeekDays.Thu
import com.example.moneyusage.frontend.helper.WeekDays.Tue
import com.example.moneyusage.frontend.helper.WeekDays.Wed

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MainContents(
    viewModel: HomeScreenViewModel,
    innerPadding: PaddingValues,
    state: LazyListState,
    restartApp: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.initialize(restartApp)
    }

    // Collect dataset as state
    val dataset = viewModel.database.collectAsState(initial = emptyList())

    /**
     * Calculate total income, expense, debt and lent
     */
    val totalIncome = dataset.value.sumOf {
        if (it.dataName == INCOME) it.amount else 0.0
    }
    val totalExpense = dataset.value.sumOf {
        if (it.dataName == EXPENSE) it.amount else 0.0
    }
    val totalDebt = dataset.value.sumOf {
        if (it.dataName == DEBT) it.amount else 0.0
    }
    val totalLent = dataset.value.sumOf {
        if (it.dataName == LENT) it.amount else 0.0
    }


    val currentAmount = mapOf(
        "Income" to totalIncome,
        "Expense" to totalExpense,
        "Debt" to totalDebt,
        "Lent" to totalLent
    )

    /**
     * Lists of pie chart data
     */
    val data = listOf(
        PieChartData(
            "Income", totalIncome,
            color = colorResource(id = R.color.income)
        ),
        PieChartData(
            "Expense", totalExpense,
            color = colorResource(id = R.color.expense)
        ),

        PieChartData(
            "Debt", totalDebt,
            color = colorResource(id = R.color.debt)
        ),
        PieChartData(
            "Lent", totalLent,
            color = colorResource(id = R.color.lent)
        ),
    )

    // Current balance card size
    val size = 250.dp

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        state = state
    ) {
        item {
            Spacer(modifier = Modifier.size(50.dp))
        }

        // Current Amount section
        currentAmountSection(data = currentAmount)()

        // Pie chart section
        pieChartSection(data = data)()

        // Time Line section
        timeLineSection()()

        item {
            // Sub Title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                Text(
                    "Recent Transactions",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(450)
                )
            }



            // Recent Transactions
            RecentTransactions()

            Spacer(modifier = Modifier.height(100.dp))


        }
    }
}