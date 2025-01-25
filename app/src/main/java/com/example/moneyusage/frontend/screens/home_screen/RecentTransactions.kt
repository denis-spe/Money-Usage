package com.example.moneyusage.frontend.screens.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.moneyusage.DEBT
import com.example.moneyusage.EXPENSE
import com.example.moneyusage.INCOME
import com.example.moneyusage.LENT
import com.example.moneyusage.R
import com.example.moneyusage.SAVING
import com.example.moneyusage.backend.models.Data
import com.example.moneyusage.frontend.helper.PaymentStatus
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
//            .sortedByDescending { it.date?.minute }
//            .sortedByDescending { it.date?.hour }
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

    val state = remember { mutableStateOf(false) }

    val isDark = isSystemInDarkTheme()

    val date = data.date
    val amount = data.amount.toMoneyFormat()

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
            .padding(start = 10.dp)
            .background(
                if (isDark) Color.DarkGray.copy(alpha = 0.1f)
                else Color.LightGray.copy(alpha = 0.1f),
                RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
            )
            .clickable {
                state.value = !state.value
            }

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
            color = categoryColor(data.category).copy(alpha = 0.5f),
            thickness = 1.dp
        )
    }

    // Show the transaction report if column is clicked
    if (state.value)
        TransactionReport(data, state)

    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun TransactionReport(data: Data, state: MutableState<Boolean>){
    val labelFontFamily = FontFamily.Monospace
    val spacerHeight = 10.dp

    Dialog(
        onDismissRequest = {
            state.value = false
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp, bottom = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Report",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Column(
                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp, end = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                data.category.toString(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                fontFamily = FontFamily.Monospace,
                            )
                            Text(
                                reportDescription(data),
                                fontSize = 15.sp,
                                fontFamily = FontFamily.Cursive,
                            )
                        }

                        reportImage(data)?.let {
                            Image(
                                painter = it,
                                contentDescription = null,
                                modifier = Modifier.size(60.dp)
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                ) {
                    HorizontalDivider(
                        color = categoryColor(data.category).copy(alpha = 0.6f)
                    )
                }
            }

            Column(
                modifier = Modifier.padding(start = 10.dp, top = 30.dp, end = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Amount", fontFamily = labelFontFamily)
                    Text(
                        "R${data.amount.toMoneyFormat()}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                Spacer(modifier = Modifier.height(spacerHeight))

                if (data.debtFrom != null && data.category == DEBT) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("A Debt From", fontFamily = labelFontFamily)
                        Text(
                            data.debtFrom.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(modifier = Modifier.height(spacerHeight))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Status", fontFamily = labelFontFamily)
                        data.paymentStatus?.name?.let {
                            Text(
                                it,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(spacerHeight))
                }

                if (data.lentTo != null && data.category == LENT) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Lent To", fontFamily = labelFontFamily)
                        Text(
                            data.lentTo.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(modifier = Modifier.height(spacerHeight))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Status", fontFamily = labelFontFamily)
                        data.paymentStatus?.name?.let {
                            Text(
                                it,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(spacerHeight))
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text("Date", fontFamily = labelFontFamily)
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Text(
                            "${data.date?.dayOfTheWeek}, ${data.date?.day} " +
                                    "${data.date?.month} ${data.date?.year}",
                            fontFamily = FontFamily.Monospace,
                            fontSize = 13.sp
                        )
                        Text(
                            "${data.date?.hour}:${data.date?.minute}"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(spacerHeight))

                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Description", fontFamily = FontFamily.Monospace)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            data.description.ifEmpty { "No Description" },
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun categoryColor(category: String?) = when (category) {
    DEBT -> colorResource(id = R.color.debt)
    INCOME -> colorResource(id = R.color.income)
    EXPENSE -> colorResource(id = R.color.expense)
    SAVING -> colorResource(id = R.color.saving)
    LENT -> colorResource(id = R.color.lent)
    else -> Color.Transparent
}

fun reportDescription(data: Data): String = when(data.category){
    DEBT -> {
        when(data.paymentStatus) {
            PaymentStatus.PAYING -> "You are a paying a debt."
            PaymentStatus.UNPAID -> "You have un paid debt."
            else -> ""
        }
    }
    INCOME -> "You have received an income."
    EXPENSE -> "You spent a given amount."
    SAVING -> "You saved a given amount."
    LENT -> {
        when(data.paymentStatus) {
            PaymentStatus.PAYING -> "You are being paid payed for a given amount"
            PaymentStatus.UNPAID -> "You lent a given amount"
            else -> ""
        }
    }
    else -> "No Description"

}

@Composable
fun reportImage(data: Data): Painter? = when(data.category){
    DEBT -> {
        when(data.paymentStatus) {
            PaymentStatus.PAYING -> painterResource(id = R.drawable.paying)
            PaymentStatus.UNPAID -> painterResource(id = R.drawable.unpaid)
            else -> null
        }
    }
    LENT -> {
        when(data.paymentStatus) {
            PaymentStatus.PAYING -> painterResource(id = R.drawable.paying)
            PaymentStatus.UNPAID -> painterResource(id = R.drawable.unpaid)
            else -> null
        }
    }
    INCOME -> painterResource(id = R.drawable.income)
    EXPENSE -> painterResource(id = R.drawable.expense)
    SAVING -> painterResource(id = R.drawable.saving)
    else -> null
}