package com.example.moneyusage.frontend.screens.home_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyusage.R
import com.example.moneyusage.frontend.components.TopAppButton
import com.example.moneyusage.frontend.dataclasses.Styles
import com.example.moneyusage.frontend.dataclasses.TopAppButtonArgs
import com.example.moneyusage.frontend.dataclasses.TopAppButtonColors

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopContents(){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        title = { TopAppBarTitle() },
        navigationIcon = { TopAppBarNavigationIcon() },
        actions = { TopAppBarActions() }
    )
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun TopAppBarTitle() {
    val styles = Styles()

    val topBarTextColor = if (isSystemInDarkTheme()) styles.darkButtonTextColor
    else styles.buttonTextColor

    val selectedButton = remember {
        mutableStateOf("All")
    }

    val primaryThemeColor = colorResource(id = R.color.primaryThemeColor)
    val secondaryThemeColor = colorResource(id = R.color.secondaryThemeColor)

    // Handle background changes on click
    val selectedButtonBackground: (label: String) -> TopAppButtonColors = {
        val bgColor = if (selectedButton.value == it) primaryThemeColor
        else secondaryThemeColor
        val textColor = if (selectedButton.value == it) Color.White
        else primaryThemeColor
        TopAppButtonColors(bgColor, textColor)
    }

    val topAppButtonArgs = listOf(
        // All arguments
        TopAppButtonArgs(
            "All",
            topBarTextColor = topBarTextColor,
            selectedButton = selectedButton,
            onClick = {
            }
        ),

        // Today argument
        TopAppButtonArgs(
            "Today",
            topBarTextColor = topBarTextColor,
            selectedButton = selectedButton,
            onClick = {
            }
        ),

        // Yesterday argument
        TopAppButtonArgs(
            "Yesterday",
            topBarTextColor = topBarTextColor,
            selectedButton = selectedButton,
            onClick = {
            }
        ),
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        topAppButtonArgs.forEach {
            Spacer(modifier = Modifier.width(5.dp))
            TopAppButton(
                topAppButtonArgs = it,
                selectedButtonBackground = selectedButtonBackground
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun TopAppBarNavigationIcon() {

    val styles = Styles()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 10.dp)
    ) {
        FilledIconButton(
            modifier = Modifier
                .size(40.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = colorResource(
                    id = R.color.profileIconTextColor
                )
            ),
            onClick = {}
        ) {

            Text(
                text = "D",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = styles.profileIconTextColor,
            )
        }
    }
}

@Composable
fun TopAppBarActions() {
    FilledIconButton(
        onClick = {},
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = colorResource(R.color.primaryThemeColor)
        )
    ) {
        Icon(
            imageVector = Icons.Default.BarChart,
            contentDescription = "Chart",
            tint = Color.White
        )
    }
}