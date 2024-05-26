package com.example.moneyusage.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
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
import com.example.moneyusage.charts.AnimatedPieChart
import com.example.moneyusage.dataclasses.PieData
import com.example.moneyusage.helper.limitMoneyDigits
import com.example.moneyusage.helper.toMoneyFormat

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun CurrentBalanceCard(
    data: List<PieData>,
    size: Dp = 200.dp,
    onClick: () -> Unit
) {
    /**
     * Current Income and expenditure  cards
     */
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = Modifier.width(size)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Pie Chart
            AnimatedPieChart(
                modifier = Modifier.padding(10.dp),
                data = data,
            )

            Row {
                // Label column
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    data.map {
                        Row {
                            Text(
                                text = it.label,
                                fontWeight = FontWeight.Bold,
                                color = it.color,
                                fontSize = 17.sp
                            )
                        }
                    }
                }
                

                // Money column
                Column(
                    modifier = Modifier.padding(5.dp),
                ) {
                    data.map {
                        // String format
                        val moneyAsString = it.value.toString()

                        // Format money
                        val formattedMoney = if (it.value < 1000000)
                            moneyAsString.toMoneyFormat()
                        else moneyAsString.limitMoneyDigits()

                        Row {
                            StyleMoneyFormat("R", formattedMoney, it.color)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StyleMoneyFormat(currencySymbol: String, formattedMoney: String, color: Color) {
    Row {
        val wholeMoney: String
        var cents: String? = null
        if (formattedMoney.contains(".") && formattedMoney.length < 6) {
            val split = formattedMoney.split(".")
            wholeMoney = split[0]
            cents = split[1]
        } else {
            wholeMoney = formattedMoney
        }

        Text(
            text = "$currencySymbol ",
            fontWeight = FontWeight.Bold,
            color = color,
            fontSize = 12.sp
        )

        Text(
            text = wholeMoney,
            fontWeight = FontWeight.Bold,
            color = color,
            fontSize = 16.sp
        )

        if (cents != null) {
            Text(
                text = cents,
                fontWeight = FontWeight.Bold,
                color = color,
                fontSize = 12.sp
            )
        }
    }
}

