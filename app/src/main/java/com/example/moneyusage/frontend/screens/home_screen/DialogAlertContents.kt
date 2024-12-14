package com.example.moneyusage.frontend.screens.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import com.example.moneyusage.DEBT
import com.example.moneyusage.EXPENSE
import com.example.moneyusage.INCOME
import com.example.moneyusage.LENT
import com.example.moneyusage.R
import com.example.moneyusage.frontend.components.AmountButton
import com.example.moneyusage.frontend.components.AmountDescriptionInputField
import com.example.moneyusage.frontend.components.AmountInputField
import com.example.moneyusage.frontend.components.DateTimePicker
import com.example.moneyusage.frontend.components.DropDownComponent
import com.example.moneyusage.frontend.dataclasses.AmountButtonState
import com.example.moneyusage.frontend.dataclasses.DialogAlertBtnListener
import com.example.moneyusage.frontend.helper.MonthNames
import com.example.moneyusage.frontend.helper.WeekDays
import kotlinx.coroutines.delay
import java.time.LocalDateTime

@Composable
fun rememberAutoUpdate(): State<LocalDateTime> {
    val state = remember { mutableStateOf(LocalDateTime.now()) }

    LaunchedEffect(Unit) {
        while (true){
            delay(1000L)
            state.value = LocalDateTime.now()
        }
    }
    return state
}

/**
 * Dialog content for floating action button
 * @param onOpenDialogState MutableState<Boolean>: The state of the floating action button
 */
@Composable
fun DialogAlertContents(
    onOpenDialogState: MutableState<Boolean>,
    viewModel: HomeScreenViewModel
) {
    val date by rememberAutoUpdate()

    val weekDays = WeekDays.entries
    val currentMonth = date.month.name.lowercase()

    val dayOfTheWeek = remember {
        mutableStateOf(TextFieldValue("${weekDays[date.dayOfWeek.value - 1]}"))
    }
    val dayOfTheMonth = remember {
        mutableStateOf(TextFieldValue(" ${date.dayOfMonth}"))
    }
    val month = remember {
        mutableStateOf(TextFieldValue(currentMonth.replaceFirstChar { it.uppercase() }))
    }
    val year = remember {
        mutableStateOf(TextFieldValue("${date.year}"))
    }
    val hour = remember {
        mutableStateOf(TextFieldValue("${date.hour}"))
    }
    val minute = remember {
        mutableStateOf(TextFieldValue("${date.minute}"))
    }

    val amountButtonState = remember { mutableStateOf(AmountButtonState.INITIAL) }
    val amountTextField = remember { mutableStateOf(TextFieldValue("")) }
    val descTextState = remember { mutableStateOf(TextFieldValue("")) }
    val selectedTextState = remember { mutableStateOf(TextFieldValue("")) }
    val selectedIconState = remember { mutableStateOf(Icons.Default.Description) }

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
    }

    if (onOpenDialogState.value)
        Dialog(onDismissRequest = {
            onOpenDialogState.value = false
        }) {
            // Draw a rectangle shape with rounded corners inside the dialog
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(580.dp)
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
                                label = "Amount Type",
                                items = listOf(INCOME, DEBT, LENT, EXPENSE),
                                selectedText = selectedTextState
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

                        item {
                            // Description input field
                            AmountDescriptionInputField(
                                state = descTextState,
                                selectedIconState = selectedIconState
                            )

                            Spacer(modifier = Modifier.height(lazySpacerHeight))
                        }

                        item {
                            // Date time picker
                            DateTimePicker(
                                weekDays = weekDays,
                                dayOfTheWeek = dayOfTheWeek,
                                dayOfTheMonth = dayOfTheMonth,
                                month = month,
                                year = year,
                                hour = hour,
                                minute = minute
                            )
                            Spacer(modifier = Modifier.height(lazySpacerHeight))
                        }

                        item {
                            // Submit button
                            AmountButton(
                                amountTextField = amountTextField,
                                financialType = selectedTextState,
                                amountButtonState = amountButtonState,
                                icon = if (selectedIconState.value != Icons.Default.Description)
                                    selectedIconState.value else Icons.Default.Add,
                                buttonColor = buttonColor.intValue,
                            ) {
                                amountButtonState.value = AmountButtonState.FINISHED
                            }
                        }
                    }
                }
            }
        }
    else {
        selectedTextState.value = TextFieldValue("")
        buttonColor.intValue = R.color.profileIconTextColor
    }
}