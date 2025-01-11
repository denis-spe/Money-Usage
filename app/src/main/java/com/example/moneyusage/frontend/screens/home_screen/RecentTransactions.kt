package com.example.moneyusage.frontend.screens.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyusage.backend.models.Data
import com.example.moneyusage.frontend.helper.limitMoneyDigits
import com.example.moneyusage.frontend.helper.toMoneyFormat
import java.math.BigDecimal

@Composable
fun RecentTransactions(dataset: State<List<Data>>) {
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
            "Recent Transactions",
            fontSize = 16.sp,
            fontWeight = FontWeight(750)
        )
    }
    Spacer(modifier = Modifier.height(20.dp))

    if (dataset.value.isNotEmpty()) {
        // Sort the data by date in descending order
        val sortedData = dataset.value.asSequence()
            .sortedByDescending { it.date?.year }
            .sortedByDescending { it.date?.month }
            .sortedByDescending { it.date?.day }
            .sortedByDescending { it.date?.minute }
            .sortedByDescending { it.date?.hour }
            .toList()

        // Display the recent transactions
        if (sortedData.size > 5)
            sortedData.subList(0, 5).forEach { data ->
                TransactionItem(data)
            }
        else {
            sortedData.forEach { data ->
                TransactionItem(data)
            }
        }
    } else{
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("No Transactions", fontSize = 20.sp)
        }
    }
}

@Composable
fun TransactionItem(data: Data) {

    val isDark = isSystemInDarkTheme()

    val date = data.date
    var amount = BigDecimal(data.amount).toPlainString()
    val wholeNumber = amount.substringBefore(".")
    var decimal = amount.substringAfter(".")
    decimal = if (decimal.length < 4) decimal else decimal.substring(0..1)

    amount = if (amount.length < 7)
        "${wholeNumber.toMoneyFormat()}.${decimal}"
    else
        data.amount.toString().limitMoneyDigits(limitMillionAndHundred = true)

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .padding(start = 10.dp)
            .background(
                if (isDark) Color.DarkGray.copy(alpha = 0.5f)
                else Color.LightGray.copy(alpha = 0.2f),
                RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
            )

    ) {
        Row(
            modifier = Modifier.padding(5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            data.icon?.let { painterResource(it) }
                ?.let {
                    Image(painter = it, contentDescription = null)
                }
            Spacer(modifier = Modifier.width(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        data.category.toString(),
                        fontSize = 16.sp, fontWeight = FontWeight.Black
                    )
                    Text("R${amount}", fontSize = 25.sp)

                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ){
                    Text(
                        "${date?.dayOfTheWeek}, ${date?.day} " +
                                "${date?.month} ${date?.year} at" +
                                " ${date?.hour}:${date?.minute}",
                        fontSize = 13.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight(600)
                    )
                    Text(
                        if (data.description.length < 20) data.description
                        else data.description.slice(0..20) + "....",
                        fontSize = 15.sp)
                }
            }
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = if(isDark) Color.DarkGray
            else Color.LightGray,
            thickness = 1.dp
        )
    }

    Spacer(modifier = Modifier.height(15.dp))
}