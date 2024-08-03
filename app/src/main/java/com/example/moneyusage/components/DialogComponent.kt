package com.example.moneyusage.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

/**
 * Handle dialogs for floating action button
 * @param state MutableState<String>: The state of the floating action button
 */
@Composable
fun DialogAlert(
    state: MutableState<String>
) {
    when (state.value) {
        "Income" -> IncomeDialAlertContent(state)
        "Expense" -> ExpenseDialAlertContent(state)
        "Debt" -> DebtDialAlertContent(state)
        "Lend" -> LendDialAlertContent(state)
    }
}

/**
 * Income dialog alert content
 * @param state MutableState<String>: The state of the floating action button
 */
@Composable
fun IncomeDialAlertContent(state: MutableState<String>) {
    val income = remember { mutableStateOf("") }

    Dialog(onDismissRequest = {
        state.value = ""
    }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column {
                Text(
                    text = "Income",
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = "Enter the amount of income you have received."
                )

                TextField(
                    value = income.value,
                    onValueChange = {
                        income.value = it
                    })

            }
        }
    }
}

/**
 * Lend dialog alert content
 * @param state MutableState<String>: The state of the floating action button
 */
@Composable
fun LendDialAlertContent(state: MutableState<String>) {
    val lend = remember { mutableStateOf("") }

    Dialog(onDismissRequest = {
        state.value = ""
    }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column {
                Text(
                    text = "Lend",
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = "Enter the amount of money you lent."
                )

                TextField(
                    value = lend.value,
                    onValueChange = {
                        lend.value = it
                    })

            }
        }
    }
}

/**
 * Debt dialog alert content
 * @param state MutableState<String>: The state of the floating action button
 */
@Composable
fun DebtDialAlertContent(state: MutableState<String>) {
    val debt = remember { mutableStateOf("") }

    Dialog(onDismissRequest = {
        state.value = ""
    }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column {
                Text(
                    text = "debt",
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = "Enter the amount you are been borrowed."
                )

                TextField(
                    value = debt.value,
                    onValueChange = {
                        debt.value = it
                    })

            }
        }
    }
}

/**
 * Expense dialog alert content
 * @param state MutableState<String>: The state of the floating action button
 */
@Composable
fun ExpenseDialAlertContent(state: MutableState<String>) {
    val expense = remember { mutableStateOf("") }

    Dialog(onDismissRequest = {
        state.value = ""
    }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column {
                Text(
                    text = "expense",
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    text = "Enter the amount of money you spent."
                )

                TextField(
                    value = expense.value,
                    onValueChange = {
                        expense.value = it
                    })

            }
        }
    }
}