package com.example.moneyusage.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.moneyusage.R
import com.example.moneyusage.dataclasses.DialogAmountState
import kotlin.concurrent.timer

/**
 * Handle dialogs for floating action button
 * @param state MutableState<String>: The state of the floating action button
 */
@Composable
fun DialogAlert(
    state: MutableState<String>,
    dialogAmountState: DialogAmountState
) {
    when (state.value) {
        "Income" -> IncomeDialAlertContent(
            dialogAmountState.income,
            desc = dialogAmountState.incomeDesc,
            state = state)
        "Expense" -> ExpenseDialAlertContent(dialogAmountState.expense, state)
        "Debt" -> DebtDialAlertContent(dialogAmountState.debt, state)
        "Lend" -> LendDialAlertContent(dialogAmountState.lend, state)
    }
}

/**
 * Income dialog alert content
 * @param state MutableState<String>: The state of the floating action button
 */
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun IncomeDialAlertContent(
    income: MutableState<TextFieldValue>,
    desc: MutableState<TextFieldValue>,
    state: MutableState<String>) {

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Income",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Text(
                    text = "Enter the amount of income you have received.",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Amount input field
                AmountInputField(state = income)

                Spacer(modifier = Modifier.height(10.dp))

                // Description input field
                DescriptionInputField(state = desc)

                Spacer(modifier = Modifier.height(10.dp))

                // Submit button
                AmountButton(textFieldState = income) {
                    Thread.sleep(9000)
                }
            }
        }
    }
}

/**
 * Lend dialog alert content
 * @param state MutableState<String>: The state of the floating action button
 */
@Composable
fun LendDialAlertContent(lend: MutableState<TextFieldValue>, state: MutableState<String>) {

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
fun DebtDialAlertContent(debt: MutableState<TextFieldValue>, state: MutableState<String>) {

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
fun ExpenseDialAlertContent(expense: MutableState<TextFieldValue>, state: MutableState<String>) {

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