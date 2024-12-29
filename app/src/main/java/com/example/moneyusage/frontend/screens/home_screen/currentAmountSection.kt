package com.example.moneyusage.frontend.screens.home_screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyusage.DEBT
import com.example.moneyusage.EXPENSE
import com.example.moneyusage.INCOME
import com.example.moneyusage.LENT
import com.example.moneyusage.backend.models.Data
import com.example.moneyusage.frontend.helper.toMoneyFormat
import kotlinx.coroutines.delay

@Composable
fun CurrentAmountSection(dataset: State<List<Data>>) {

    // Create a map of the data
    val data = mapOf(
        INCOME to dataset.value.sumOf { if (it.dataName == INCOME) it.amount else 0.0 },
        EXPENSE to dataset.value.sumOf { if (it.dataName == EXPENSE) it.amount else 0.0 },
        DEBT to dataset.value.sumOf { if (it.dataName == DEBT) it.amount else 0.0 },
        LENT to dataset.value.sumOf { if (it.dataName == LENT) it.amount else 0.0 }
    )

    // Space up from the top of the screen
    Spacer(modifier = Modifier.size(50.dp))

    val numbers = remember { mutableStateOf(listOf(0f)) }

    // Create anima table values for each item in the list
    val animatedValues = remember(numbers.value) {
        numbers.value.map {
            Animatable(it)
        }
    }

    // Automatically trigger animations on the list values
    LaunchedEffect(Unit) {
        while (true) {
            if (numbers.value[0] < data.size - 1)
                numbers.value = numbers.value.map { it + 1f }
            else
                numbers.value = listOf(0f)

            // Animate each value
            numbers.value.forEachIndexed { index, value ->
                animatedValues[index].animateTo(
                    targetValue = value,
                    animationSpec = tween(durationMillis = 1000, easing = EaseIn)
                )
            }

            // Delay before updating the values again
            delay(5000)  // Wait for 2 seconds before updating again
        }
    }


    val index = animatedValues[0].value.toInt()

    Column(
        modifier = Modifier.padding(start = 10.dp)
    ) {

        Column {
            Row(
                modifier = Modifier.fillMaxWidth()

            ) {
                val amount = data.values.toList()[index].toString()
                val wholeNum = amount.split(".")[0].toMoneyFormat()
                val decimalNum = amount.split(".")[1]

                Text(
                    "R${wholeNum}." + decimalNum,
                    fontSize = 40.sp
                )

            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Current ${data.keys.toList()[index]}", fontSize = 15.sp,
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))

}