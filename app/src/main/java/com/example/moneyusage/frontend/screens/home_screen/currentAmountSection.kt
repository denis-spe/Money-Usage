package com.example.moneyusage.frontend.screens.home_screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.extensions.roundTwoDecimal
import com.example.moneyusage.frontend.helper.toMoneyFormat
import kotlinx.coroutines.delay

fun currentAmountSection(data: Map<String, Double>): LazyListScope.() -> Unit{
    return {
        item {
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
                            animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
                        )
                    }

                    // Delay before updating the values again
                    delay(3000)  // Wait for 2 seconds before updating again
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
                        val amount = data.values.toList()[index].roundTwoDecimal().toString()
                        val wholeNum = amount.split(".")[0].toMoneyFormat()
                        val decimalNum = amount.split(".")[1]

                        Text("R${wholeNum}." + decimalNum,
                            fontSize = 40.sp)

                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Current ${data.keys.toList()[index]}", fontSize = 15.sp,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}