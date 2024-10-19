package com.example.moneyusage.frontend.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.moneyusage.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropDownComponent(label: String,
                          items: List<T>,
                          selectedText: MutableState<TextFieldValue>,
                          modifier: Modifier = Modifier){
    // Expend state
    var expended by remember {
        mutableStateOf(false)
    }

    val colorResource = colorResource(id = R.color.authorization_color)

    Box {
        ExposedDropdownMenuBox(
            expanded = expended,
            onExpandedChange = {
                expended = !expended
            }
        ) {
            Box(modifier = modifier){
                // Text field
                OutlinedTextField(
                    value = selectedText.value,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    label = {
                        Text(text = label)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colorResource,
                        focusedLabelColor = colorResource,
                        focusedTrailingIconColor = colorResource,
                    ),
                    trailingIcon = {
                        if (expended) Icon(imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "expended up icon") else
                            Icon(imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = "expended down icon")
                    }
                )
            }
            ExposedDropdownMenu(expanded = expended, onDismissRequest = {
                expended = false
            }) {
                items.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it.toString()) },
                        onClick = {
                            selectedText.value = TextFieldValue(it.toString())
                            expended = false
                        })
                }
            }
        }
    }
}