package com.example.moneyusage.frontend.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.moneyusage.R
import com.example.moneyusage.frontend.helper.MonthNames
import com.example.moneyusage.frontend.helper.WeekDays
import java.time.LocalDateTime
import java.util.Calendar
import kotlin.enums.EnumEntries

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePicker(
    weekDays: EnumEntries<WeekDays>,
    dayOfTheWeek: MutableState<TextFieldValue>,
    dayOfTheMonth: MutableState<TextFieldValue>,
    month: MutableState<TextFieldValue>,
    year: MutableState<TextFieldValue>,
    hour: MutableState<TextFieldValue>,
    minute: MutableState<TextFieldValue>
){

    var onDismissState by remember { mutableStateOf(false) }
    val dayOfWeek = dayOfTheWeek.value.text
    val dayOfMonth = dayOfTheMonth.value.text

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton (onClick = {onDismissState = true}) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = "Current date",
                    modifier = Modifier.size(20.dp)
                )
                
                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        """
            $dayOfWeek, $dayOfMonth ${month.value.text} ${year.value.text}
            """.trimIndent()
                    )

                    val addZoneOnHour = if (hour.value.text.length == 1)
                        "0${hour.value.text}" else hour.value.text
                    val addZoneOnMinute = if (minute.value.text.length == 1)
                        "0${minute.value.text}" else minute.value.text

                    Text("Time: ${addZoneOnHour}:${addZoneOnMinute}")
                }
            }

        }
    }

    if (onDismissState){
        Dialog(onDismissRequest = {
            onDismissState = false
        }) {
            DateTimeComponent(
                weekDays = weekDays,
                dayOfTheWeek = dayOfTheWeek,
                dayOfTheMonth = dayOfTheMonth,
                month = month,
                year = year,
                hour = hour,
                minute = minute
            )
        }
    }
}


@Composable
fun DateTimeComponent(
    weekDays: EnumEntries<WeekDays>,
    dayOfTheWeek: MutableState<TextFieldValue>,
    dayOfTheMonth: MutableState<TextFieldValue>,
    month: MutableState<TextFieldValue>,
    year: MutableState<TextFieldValue>,
    hour: MutableState<TextFieldValue>,
    minute: MutableState<TextFieldValue>
){
    val calendar = remember {
        mutableStateOf(
            Calendar.getInstance()
        )
    }
    val date = LocalDateTime.now()
    val monthName = MonthNames.entries
    val lastOfTheMonth = calendar.value.getActualMaximum(
        Calendar.DAY_OF_MONTH
    )

    Card(
        modifier = Modifier.fillMaxWidth(0.9f)
            .fillMaxHeight(0.2f)
            .alpha(0.95f),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 900.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                DropDownComponent(
                    items = weekDays,
                    selectedText = dayOfTheWeek,
                    modifier = Modifier.width(60.dp),
                    showTailingBtn = false,
                    textAlign = TextAlign.Center,
                    alignContentTextToContent = true
                )

                DropDownComponent(
                    items = (1..lastOfTheMonth).toList(),
                    selectedText = dayOfTheMonth,
                    modifier = Modifier.width(60.dp),
                    showTailingBtn = false,
                    textAlign = TextAlign.Center,
                    alignContentTextToContent = true
                )

                DropDownComponent(
                    items = monthName,
                    selectedText = month,
                    modifier = Modifier.width(100.dp),
                    showTailingBtn = false,
                    textAlign = TextAlign.Center,
                    alignContentTextToContent = true
                )

                DropDownComponent(
                    items = (1997..date.year).toList(),
                    selectedText = year,
                    modifier = Modifier.width(80.dp),
                    showTailingBtn = false,
                    textAlign = TextAlign.Center,
                    alignContentTextToContent = true
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                val hours = (0..24).toList().map {
                    if (it < 10) "0$it" else "$it"
                }

                DropDownComponent(
                    items = hours,
                    selectedText = hour,
                    modifier = Modifier.width(60.dp),
                    showTailingBtn = false,
                    textAlign = TextAlign.Center,
                    alignContentTextToContent = true
                )
                Text(":")
                DropDownComponent(
                    items = (0..60).toList(),
                    selectedText = minute,
                    modifier = Modifier.width(60.dp),
                    showTailingBtn = false,
                    textAlign = TextAlign.Center,
                    alignContentTextToContent = true
                )
            }
        }
    }
}

@Preview
@Composable
fun DateTimeComponentPreview(){

}