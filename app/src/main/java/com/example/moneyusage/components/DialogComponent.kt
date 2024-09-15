package com.example.moneyusage.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.moneyusage.R
import com.example.moneyusage.dataclasses.AmountButtonState
import com.example.moneyusage.dataclasses.DialogAmountState

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
            state = state
        )
        "Expense" -> ExpenseDialAlertContent(
            dialogAmountState.expense,
            desc = dialogAmountState.expenseDesc,
            state = state
        )
        "Debt" -> DebtDialAlertContent(
            dialogAmountState.debt,
            desc = dialogAmountState.debtDesc,
            state = state
        )
        "Lend" -> LendDialAlertContent(
            dialogAmountState.lend,
            desc = dialogAmountState.lendDesc,
            state = state
        )
    }
}

/**
 * Dialog content for floating action button
 * @param state MutableState<String>: The state of the floating action button
 * @param amountButtonState MutableState<String>: The state of the amount button
 * @param amountTextField MutableState<TextFieldValue>: The state of the amount text field
 * @param desc MutableState<TextFieldValue>: The state of the description text field
 * @param title String: The title of the dialog
 * @param subTitle String: The subtitle of the dialog
 * @param icon Int: The icon of the dialog
 * @param buttonColor Int: The color of the button
 * @param dataHandling () -> Unit: The function to handle the data
 */
@Composable
fun DialogContent(
    state: MutableState<String>,
    amountButtonState: MutableState<AmountButtonState>,
    amountTextField: MutableState<TextFieldValue>,
    desc: MutableState<TextFieldValue>,
    title: String,
    subTitle: String,
    icon: Int,
    buttonColor: Int,
    dataHandling: () -> Unit
){
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
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Text(
                    text = subTitle,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Amount input field
                AmountInputField(
                    amountButtonState = amountButtonState,
                    state = amountTextField
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Description input field
                AmountDescriptionInputField(state = desc)

                Spacer(modifier = Modifier.height(10.dp))

                // Submit button
                AmountButton(
                    amountTextField = amountTextField,
                    amountButtonState = amountButtonState,
                    icon = icon,
                    buttonColor = buttonColor,
                    waitingListener = dataHandling
                )
            }
        }
    }
}

/**
 * Income dialog alert content
 * @param state MutableState<String>: The state of the floating action button
 */
@Composable
fun IncomeDialAlertContent(
    income: MutableState<TextFieldValue>,
    desc: MutableState<TextFieldValue>,
    state: MutableState<String>) {

    // State of the amount button
    val amountButtonState = remember {
        mutableStateOf(AmountButtonState.INITIAL)
    }

    DialogContent(
        state = state,
        amountButtonState = amountButtonState,
        amountTextField = income,
        desc = desc,
        title = "Income",
        subTitle = "Enter the amount of money you have earned.",
        icon = R.drawable.outline_income,
        buttonColor = R.color.incomeCloseColor
    ) {
        amountButtonState.value = AmountButtonState.FINISHED
    }
}

/**
 * Lend dialog alert content
 * @param state MutableState<String>: The state of the floating action button
 */
@Composable
fun LendDialAlertContent(
    lend: MutableState<TextFieldValue>,
    desc: MutableState<TextFieldValue>,
    state: MutableState<String>
) {
    // State of the amount button
    val amountButtonState = remember {
        mutableStateOf(AmountButtonState.INITIAL)
    }

    DialogContent(
        state = state,
        amountButtonState = amountButtonState,
        amountTextField = lend,
        desc = desc,
        title = "Lend",
        subTitle = "Enter the amount of money you want to lend.",
        icon = R.drawable.outline_lend,
        buttonColor = R.color.lendCloseColor
    ) {
        amountButtonState.value = AmountButtonState.FINISHED
    }
}

/**
 * Debt dialog alert content
 * @param state MutableState<String>: The state of the floating action button
 */
@Composable
fun DebtDialAlertContent(
    debt: MutableState<TextFieldValue>,
    desc: MutableState<TextFieldValue>,
    state: MutableState<String>) {

    // State of the amount button
    val amountButtonState = remember {
        mutableStateOf(AmountButtonState.INITIAL)
    }

    DialogContent(
        state = state,
        amountButtonState = amountButtonState,
        amountTextField = debt,
        desc = desc,
        title = "Debt",
        subTitle = "Enter the amount of money you owe.",
        icon = R.drawable.outline_debt,
        buttonColor = R.color.debtCloseColor
    ) {
        amountButtonState.value = AmountButtonState.FINISHED
    }
}

/**
 * Expense dialog alert content
 * @param state MutableState<String>: The state of the floating action button.
 */
@Composable
fun ExpenseDialAlertContent(
    expense: MutableState<TextFieldValue>,
    desc: MutableState<TextFieldValue>,
    state: MutableState<String>
) {
    // State of the amount button
    val amountButtonState = remember {
        mutableStateOf(AmountButtonState.INITIAL)
    }

    DialogContent(
        state = state,
        amountButtonState = amountButtonState,
        amountTextField = expense,
        desc = desc,
        title = "Expense",
        subTitle = "Enter the amount of money you have spent.",
        icon = R.drawable.outline_expense,
        buttonColor = R.color.expenseCloseColor
    ) {
        amountButtonState.value = AmountButtonState.FINISHED
    }
}