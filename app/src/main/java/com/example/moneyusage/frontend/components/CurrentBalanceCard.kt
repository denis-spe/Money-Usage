package com.example.moneyusage.frontend.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyusage.frontend.charts.AnimatedPieChart
import com.example.moneyusage.frontend.dataclasses.PieChartData
import com.example.moneyusage.frontend.helper.limitMoneyDigits

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun CurrentBalanceCard(
    data: List<PieChartData>,
    onClick: () -> Unit
) {
    /**
     * Current Income and expenditure  cards
     */


}

@Composable
fun StyleMoneyFormat(currencySymbol: String, formattedMoney: String, color: Color) {
    Row {
        Text(
            text = "$currencySymbol ",
            fontWeight = FontWeight.Bold,
            color = color,
            fontSize = 8.sp
        )

        Text(
            text = formattedMoney.limitMoneyDigits(limitMillonAndHundred = true),
            fontWeight = FontWeight.Bold,
            color = color,
            fontSize = 14.sp
        )
    }
}

