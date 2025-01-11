package com.example.moneyusage.frontend.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.moneyusage.frontend.dataclasses.DateTime
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDismiss: MutableState<Boolean>,
    onDateSelected: (DateTime) -> Unit
) {
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())

    DatePickerDialog(
        modifier = Modifier.alpha(0.9f),
        onDismissRequest = { onDismiss.value = false },
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis.convertMillisToDate())
                onDismiss.value = false
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismiss.value = false
            }) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onDismiss: MutableState<Boolean>,
    onTimeSelected: (TimePickerState) -> Unit
) {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Dialog(onDismissRequest = {
        onDismiss.value = false
    }) {
        Card(
            modifier = Modifier.alpha(0.9f)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Select time")
                Spacer(modifier = Modifier.height(16.dp))
                TimePicker(
                    state = timePickerState,
                )
            }

            Row(
                modifier = Modifier.padding(top = 10.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = {
                    timePickerState.hour = currentTime.get(Calendar.HOUR_OF_DAY)
                    timePickerState.minute = currentTime.get(Calendar.MINUTE)
                    onTimeSelected(timePickerState)
                    onDismiss.value = false
                }) {
                    Text("Cancel")
                }

                TextButton(onClick = {
                    onTimeSelected(timePickerState)
                    onDismiss.value = false
                }) {
                    Text("OK")
                }
            }
        }
    }
}

fun Long?.convertMillisToDate(use24: Boolean=true): DateTime {
    val date = this?.let { Date(it) }
    // Format for day name
    val dayFormat = SimpleDateFormat("E", Locale.ENGLISH)
    val dayName = date?.let { dayFormat.format(it) }

    // Format for month name
    val monthFormat = SimpleDateFormat("MMM", Locale.ENGLISH)
    val monthName = date?.let { monthFormat.format(it) }

    // Format for year
    val yearFormat = SimpleDateFormat("yyyy", Locale.ENGLISH)
    val year = date?.let { yearFormat.format(it) }

    // Format for day of the month
    val dayOfMonthFormat = SimpleDateFormat("d", Locale.ENGLISH)
    val dayOfMonth = date?.let { dayOfMonthFormat.format(it) }

    val currentTime = Calendar.getInstance()
    val initialHour = currentTime.get(if (use24) Calendar.HOUR_OF_DAY else Calendar.HOUR)
    val initialMinute = currentTime.get(Calendar.MINUTE)

    // Initialize the Datetime object
    val dateTime = DateTime(
        year = year?.toInt(),
        month = monthName,
        day = dayOfMonth?.toInt(),
        hour = initialHour,
        minute = initialMinute,
        dayOfTheWeek = dayName
    )

    return dateTime
}