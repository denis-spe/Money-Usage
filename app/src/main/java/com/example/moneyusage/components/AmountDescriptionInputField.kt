package com.example.moneyusage.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CarRental
import androidx.compose.material.icons.filled.Carpenter
import androidx.compose.material.icons.filled.CastForEducation
import androidx.compose.material.icons.filled.Chair
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.DesktopMac
import androidx.compose.material.icons.filled.DesktopWindows
import androidx.compose.material.icons.filled.EmojiTransportation
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.FreeBreakfast
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.Laptop
import androidx.compose.material.icons.filled.LocalGroceryStore
import androidx.compose.material.icons.filled.LocalPizza
import androidx.compose.material.icons.filled.Motorcycle
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.PhoneIphone
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.filled.SportsMotorsports
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.Tablet
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Water
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.isEmpty
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlin.text.contains

@Composable
fun DescriptionIconsDialog(
    state: MutableState<Boolean>,
    selectedIcon: MutableState<ImageVector>
){
    val searchState = remember {
        mutableStateOf(TextFieldValue())
    }

    val originalIconList = mapOf(
        "Agriculture" to Icons.Default.Agriculture,
        "Balance" to Icons.Default.Balance,
        "AttachMoney" to Icons.Default.AttachMoney,
        "AccountBalance" to Icons.Default.AccountBalance,
        "AccountBalanceWallet" to Icons.Default.AccountBalanceWallet,
        "Payments" to Icons.Default.Payments,
        "TrendingUp" to Icons.Default.TrendingUp,
        "Work" to Icons.Default.Work,
        "Business" to Icons.Default.Business,
        "Storefront" to Icons.Default.Storefront,
        "LocalGroceryStore" to Icons.Default.LocalGroceryStore,
        "EmojiTransportation" to Icons.Default.EmojiTransportation,
        "CleaningServices" to Icons.Default.CleaningServices,
        "Fastfood" to Icons.Default.Fastfood,
        "Water" to Icons.Default.Water,
        "Payment" to Icons.Default.Payment,
        "Sell" to Icons.Default.Sell,
        "DesktopMac" to Icons.Default.DesktopMac,
        "Paid" to Icons.Default.Paid,
        "Description" to Icons.Default.Description,
        "Build" to Icons.Default.Build,
        "Laptop" to Icons.Default.Laptop,
        "DesktopWindows" to Icons.Default.DesktopWindows,
        "Tablet" to Icons.Default.Tablet,
        "PhoneAndroid" to Icons.Default.PhoneAndroid,
        "PhoneIphone" to Icons.Default.PhoneIphone,
        "SportsMotorsports" to Icons.Default.SportsMotorsports,
        "Motorcycle" to Icons.Default.Motorcycle,
        "Chair" to Icons.Default.Chair,
        "Cake" to Icons.Default.Cake,
        "CameraAlt" to Icons.Default.CameraAlt,
        "Camera" to Icons.Default.Camera,
        "CarRental" to Icons.Default.CarRental,
        "CarRental" to Icons.Default.Carpenter,
        "CastForEducation" to Icons.Default.CastForEducation,
        "CreditCard" to Icons.Default.CreditCard,
        "Savings" to Icons.Default.Savings,
        "Store" to Icons.Default.Store,
        "Hotel" to Icons.Default.Hotel,
        "House" to Icons.Default.House,
        "LocalPizza" to Icons.Default.LocalPizza,
        "FreeBreakfast" to Icons.Default.FreeBreakfast
    )

    val filteredList = remember {
        mutableStateOf(originalIconList)
    }

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
                                Icon(
                                    imageVector = icon,
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
    state: MutableState<TextFieldValue>
){
    val fontWeight = FontWeight.Bold
    val fontSize = 20.sp

    val dialogState = remember {
        mutableStateOf(false)
    }

    val selectedIconState = remember {
        mutableStateOf(Icons.Default.Description)
    }

    val descIcon = Icons.Default.Description

    val selectedIcon = if(selectedIconState.value != descIcon)
        selectedIconState.value
    else descIcon

    TextField(
        value = TextFieldValue(
            text = state.value.text,
            selection = TextRange(state.value.text.length)
        ),
        onValueChange = {
            state.value = it
        },
        leadingIcon = {
            IconButton(
                onClick = {
                    dialogState.value = true
                }
            ) {
                Icon(imageVector =selectedIcon,
                    contentDescription = "description")
            }
        },
        textStyle = TextStyle(
            fontWeight = fontWeight,
            fontSize = fontSize),

        placeholder = {
            Text(
                text = "description (optional)",
                fontWeight = fontWeight,
                fontSize = fontSize,
                color = Color.Gray
            )
        },
    )

    if(dialogState.value)
        DescriptionIconsDialog(
            state = dialogState,
            selectedIcon = selectedIconState
        )
}
