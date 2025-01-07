package com.example.moneyusage.frontend.screens.home_screen

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.moneyusage.backend.models.Data

@Composable
fun RecentTransactions(dataset: State<List<Data>>) {
    // Sort the data by date in descending order
    val sortedData = dataset.value.sortedByDescending { it.date?.year }
        .sortedByDescending { it.date?.month }
        .sortedByDescending { it.date?.day }
        .sortedByDescending { it.date?.hour }

    // Display the recent transactions
    sortedData.forEach { data ->
        TransactionItem(data)
    }
}

@Composable
fun TransactionItem(data: Data) {
    // Display the transaction details
    Text(text = data.amount.toString())
    Text(text = data.date.toString())
    Text(text = data.description)

    data.icon?.let { painterResource(it) }?.let { Image(painter = it, contentDescription = null) }
}