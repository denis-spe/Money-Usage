package com.example.moneyusage.frontend.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.moneyusage.R
import com.example.moneyusage.backend.models.Data
import kotlin.reflect.typeOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropDownComponent(
    items: List<T>,
    selectedText: MutableState<TextFieldValue>,
    selectedData: MutableState<Data>? = null,
    label: String? = null,
    placeHolder: String? = null,
    showTailingBtn: Boolean = true,
    readOnly: Boolean = true,
    textAlign: TextAlign = TextAlign.Start,
    alignContentTextToContent: Boolean = false,
    modifier: Modifier = Modifier
) {
    // Expend state
    var expended = remember {
        mutableStateOf(false)
    }

    var filterItems = remember {
        mutableStateOf(items)
    }

    val trailingIcon: @Composable() (() -> Unit) = {
        if (showTailingBtn) {
            if (expended.value) Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "expended up icon"
            ) else
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "expended down icon"
                )

        }
    }

    val colorResource = colorResource(id = R.color.authorization_color)

    Box {
        ExposedDropdownMenuBox(
            expanded = expended.value,
            onExpandedChange = {
                expended.value = !expended.value
            }
        ) {
            Box(modifier = modifier) {
                // Text field
                TextField(
                    placeholder = {
                        if (placeHolder != null)
                            Text(
                                text = placeHolder,
                                fontWeight = FontWeight.Bold
                            )
                    },
                    value = selectedText.value,
                    onValueChange = {
                        if (!readOnly) {
                            selectedText.value = it
                            filterItems.value = items.filter { item ->
                                item.toString().contains(selectedText.value.text, ignoreCase = true)
                            }
                        }
                    },
                    readOnly = readOnly,
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    label = {
                        if (label != null)
                            Text(
                                text = label,
                                fontWeight = FontWeight.Bold
                            )
                    },
                    textStyle = TextStyle(textAlign = textAlign, fontWeight = FontWeight.Bold),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colorResource,
                        focusedLabelColor = colorResource,
                        focusedTrailingIconColor = colorResource,
                    ),
                    trailingIcon = if (showTailingBtn && items.isNotEmpty()) trailingIcon else null
                )
            }
            if (items.isNotEmpty())
                ExposedDropdownMenu(expanded = expended.value, onDismissRequest = {
                    expended.value = false
                }) {
                    if (readOnly)
                        items.forEach {
                            DropDownItem(
                                it,
                                selectedText,
                                expended,
                                selectedData,
                                alignContentTextToContent
                            )
                        }
                    else {
                        filterItems.value.forEach {
                            DropDownItem(
                                it,
                                selectedText,
                                expended,
                                selectedData,
                                alignContentTextToContent
                            )
                        }
                    }
                }
        }
    }
}

@Composable
fun <T> DropDownItem(
    item: T,
    selectedText: MutableState<TextFieldValue>,
    expended: MutableState<Boolean>,
    selectedData: MutableState<Data>? = null,
    alignContentTextToContent: Boolean = false
){
    DropdownMenuItem(
        text = {
            if (alignContentTextToContent) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = item.toString(),
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                Text(
                    text = item.toString(),
                    fontWeight = FontWeight.Bold
                )
            }
        },
        leadingIcon = null,
        trailingIcon = null,
        onClick = {
            selectedText.value = TextFieldValue(item.toString())
            if (selectedData != null)
                selectedData.value = item as Data
            expended.value = false
        })
}