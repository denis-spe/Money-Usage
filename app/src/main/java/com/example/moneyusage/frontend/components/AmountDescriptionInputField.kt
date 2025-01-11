package com.example.moneyusage.frontend.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.moneyusage.R


@Composable
fun DescriptionIconsDialog(
    state: MutableState<Boolean>,
    selectedIcon: MutableState<Int>
){
    val searchState = remember {
        mutableStateOf(TextFieldValue())
    }

    // Original icon list
    val originalIconList = dialogIcons()

    // Filtered icon list
    val filteredList = remember {
        mutableStateOf(originalIconList)
    }

    // Search font size
    val searchFontSize = 16.sp

    Dialog(onDismissRequest = {
        state.value = false
    }) {
        Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(375.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(18.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 18.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Select an icon",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                OutlinedTextField(
                    value = searchState.value,
                    onValueChange = {
                        searchState.value = it
                        filteredList.value = originalIconList.filter { pair ->
                            pair.key.contains(searchState.value.text, ignoreCase = true)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 18.dp),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true,
                    textStyle = TextStyle(fontSize = searchFontSize),
                    placeholder = {
                        Text(
                            text = "Search",
                            fontSize = searchFontSize,
                        )
                    }
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3)
                ) {
                    items(filteredList.value.keys.size) { it ->

                        val name = filteredList.value.keys.elementAt(it)
                        val icon = filteredList.value.values.elementAt(it)

                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                onClick = {
                                    selectedIcon.value = icon
                                    state.value = false
                                }){
                                Image(
                                    painter = painterResource(icon),
                                    contentDescription = name
                                )
                            }

                            Text(
                                text = name,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AmountDescriptionInputField(
    state: MutableState<TextFieldValue>,
    selectedIconState: MutableState<Int>
){
    val fontWeight = FontWeight.Bold
    val fontSize = 20.sp

    val dialogState = remember {
        mutableStateOf(false)
    }

    val descIcon = R.drawable.description

    // Handle icon change
    val selectedIcon = if(selectedIconState.value != descIcon)
        selectedIconState.value
    else descIcon

    TextField(
        value = TextFieldValue(
            text = state.value.text,
            selection = TextRange(state.value.text.length)
        ),
        onValueChange = {
            if (it.text.length <= 52)
                state.value = it.copy(selection = it.selection)
        },
        leadingIcon = {
            IconButton(
                onClick = {
                    dialogState.value = true
                }
            ) {
                Image(painter = painterResource(selectedIcon),
                    contentDescription = "description")
            }
        },
        textStyle = TextStyle(
            fontWeight = fontWeight,
            fontSize = fontSize),

        placeholder = {
            Text(
                text = "Optional",
                fontWeight = fontWeight,
                fontSize = fontSize,
                color = Color.Gray
            )
        },
        label = {
            Text(
                "Description",
                fontWeight = FontWeight.Bold
            )
        },
        keyboardActions = KeyboardActions.Default,
        visualTransformation = VisualTransformation.None
    )

    if(dialogState.value)
        DescriptionIconsDialog(
            state = dialogState,
            selectedIcon = selectedIconState
        )
}
