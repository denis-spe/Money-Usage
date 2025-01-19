package com.example.moneyusage.frontend.screens.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.moneyusage.DEBT
import com.example.moneyusage.EXPENSE
import com.example.moneyusage.INCOME
import com.example.moneyusage.LENT
import com.example.moneyusage.R
import com.example.moneyusage.SAVING
import com.example.moneyusage.frontend.components.AmountButton
import com.example.moneyusage.frontend.components.AmountDescriptionInputField
import com.example.moneyusage.frontend.components.AmountInputField
import com.example.moneyusage.frontend.components.AmountUserInput
import com.example.moneyusage.frontend.components.DatePickerModal
import com.example.moneyusage.frontend.components.DropDownComponent
import com.example.moneyusage.frontend.components.TimePickerDialog
import com.example.moneyusage.frontend.components.convertMillisToDate
import com.example.moneyusage.frontend.dataclasses.AmountButtonState


/**
 * Dialog content for floating action button
 * @param onOpenDialogState MutableState<Boolean>: The state of the floating action button
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogAlertContents(
    onOpenDialogState: MutableState<Boolean>,
    viewModel: HomeScreenViewModel
) {
    // Collect dataset as state
    val dataset = viewModel.database.collectAsState(initial = emptyList())
    viewModel.fetchData()

    // Get all the un payed debts
    val debts = dataset.value.filter {
        it.isItPayed == false && it.category == DEBT
    }.map {
        it.debtFrom
    }

    // Get the selected date
    var selectedDateTime by remember { mutableStateOf(System.currentTimeMillis().convertMillisToDate()) }

    // Handle the dismissed date state
    val dismissedDate = remember { mutableStateOf(false) }
    // Handle the dismissed time state
    val dismissedTime = remember { mutableStateOf(false) }


    val amountButtonState = remember { mutableStateOf(AmountButtonState.INITIAL) }
    val amountTextField = remember { mutableStateOf(TextFieldValue("")) }
    val descTextState = remember { mutableStateOf(TextFieldValue("")) }
    val selectedTextState = remember { mutableStateOf(TextFieldValue("")) }
    val selectedIconState = remember { mutableIntStateOf(R.drawable.description) }
    val selectedPayingDebtState = remember { mutableStateOf(TextFieldValue("")) }
    val debtFromState = remember { mutableStateOf(TextFieldValue("")) }

    val buttonColor = remember { mutableIntStateOf(R.color.profileIconTextColor) }
    when (selectedTextState.value.text) {
        INCOME -> {
            buttonColor.intValue = R.color.income
            amountButtonState.value = AmountButtonState.INITIAL
        }

        EXPENSE -> {
            buttonColor.intValue = R.color.expense
            amountButtonState.value = AmountButtonState.INITIAL
        }

        LENT -> {
            buttonColor.intValue = R.color.lent
            amountButtonState.value = AmountButtonState.INITIAL
        }

        DEBT -> {
            buttonColor.intValue = R.color.debt
            amountButtonState.value = AmountButtonState.INITIAL
        }

        SAVING -> {
            buttonColor.intValue = R.color.saving
            amountButtonState.value = AmountButtonState.INITIAL
        }
    }

    if (onOpenDialogState.value)
        Dialog(onDismissRequest = {
            onOpenDialogState.value = false
        }) {
            // Draw a rectangle shape with rounded corners inside the dialog
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(607.dp)
                    .padding(16.dp)
                    .alpha(0.90f),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        text = "Money usage",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp
                    )

                    Text(
                        text = "Add person finance amounts",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )

                    if (selectedTextState.value.text.isNotEmpty()){
                        Text(
                            text = selectedTextState.value.text,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    val lazySpacerHeight = 15.dp

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        item {
                            DropDownComponent(
                                label = "Category",
                                items = listOf(INCOME, DEBT, LENT, EXPENSE, SAVING),
                                selectedText = selectedTextState
                            )

                            Spacer(modifier = Modifier.height(lazySpacerHeight))
                        }

                        if (selectedTextState.value.text == DEBT && debts.isNotEmpty())
                            item {
                                    DropDownComponent(
                                        label = "Paying A Debt",
                                        items = debts,
                                        selectedText = selectedPayingDebtState
                                    )

                                    Spacer(modifier = Modifier.height(lazySpacerHeight))
                            }

                        item {
                            // Amount input field
                            AmountInputField(
                                amountButtonState = amountButtonState,
                                state = amountTextField
                            )

                            Spacer(modifier = Modifier.height(lazySpacerHeight))
                        }

                        if (selectedTextState.value.text == DEBT &&
                            selectedPayingDebtState.value.text.isEmpty())
                            item {
                                AmountUserInput(
                                    state = debtFromState,
                                    label = "A Debt From",
                                    placeholder = "Borrowing or getting money from"
                                )

                                Spacer(modifier = Modifier.height(lazySpacerHeight))
                            }

                        item {
                            // Description input field
                            AmountDescriptionInputField(
                                state = descTextState,
                                selectedIconState = selectedIconState
                            )

                            Spacer(modifier = Modifier.height(lazySpacerHeight))
                        }

                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextButton(
                                    shape = RoundedCornerShape(10.dp),
                                    contentPadding = PaddingValues(2.dp),
                                    onClick = {
                                        dismissedDate.value = true
                                    }) {
                                    Icon(
                                        imageVector = Icons.Default.DateRange,
                                        contentDescription = "Date",
                                        modifier = Modifier.padding(end = 8.dp)
                                    )

                                    Text(
                                        "${selectedDateTime.dayOfTheWeek}, " +
                                                "${selectedDateTime.day} ${selectedDateTime.month} " +
                                                "${selectedDateTime.year}"
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextButton(
                                    shape = RoundedCornerShape(10.dp),
                                    contentPadding = PaddingValues(2.dp),
                                    onClick = {
                                    dismissedTime.value = true
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.AccessTime,
                                        contentDescription = "Time",
                                        modifier = Modifier.padding(end = 8.dp)
                                    )

                                    Text("${if (selectedDateTime.hour!! < 10) 
                                        "0${selectedDateTime.hour}" else selectedDateTime.hour}:" +
                                            "${if (selectedDateTime.minute!! < 10) 
                                                "0${selectedDateTime.minute}" 
                                            else selectedDateTime.minute}")
                                }
                            }


                            if (dismissedTime.value)
                                TimePickerDialog(onDismiss = dismissedTime){
                                    selectedDateTime = selectedDateTime.copy(hour = it.hour)
                                    selectedDateTime = selectedDateTime.copy(minute = it.minute)
                                }

                            // Show the date if true
                            if (dismissedDate.value)
                                DatePickerModal(
                                    onDateSelected = { selectedDateTime = it },
                                    onDismiss = dismissedDate,
                                )
                            Spacer(modifier = Modifier.height(lazySpacerHeight))
                        }

                        item {
                            // Submit button
                            AmountButton(
                                amountTextField = amountTextField,
                                financialType = selectedTextState,
                                amountButtonState = amountButtonState,
                                debtFromState = debtFromState,
                                selectedPayingDebtState = selectedPayingDebtState,
                                icon = if (selectedIconState.intValue != R.drawable.description)
                                    selectedIconState.intValue else R.drawable.add,
                                buttonColor = buttonColor.intValue,
                            ) {
                                viewModel.onDateSaveClick(
                                    dataName = selectedTextState.value.text,
                                    amount = amountTextField.value.text
                                        .replace(",", "")
                                        .toDouble(),
                                    description = descTextState.value.text,
                                    date = selectedDateTime,
                                    icon = selectedIconState.intValue,
                                    debtFrom = debtFromState.value.text
                                )
                                amountButtonState.value = AmountButtonState.FINISHED
                            }

                        }
                    }
                }
            }
        }
    else {
        amountTextField.value = TextFieldValue("")
        amountButtonState.value = AmountButtonState.INITIAL
        descTextState.value = TextFieldValue("")
        selectedIconState.intValue = R.drawable.description
        selectedTextState.value = TextFieldValue("")
        buttonColor.intValue = R.color.profileIconTextColor
        selectedDateTime = System.currentTimeMillis().convertMillisToDate()
    }
}