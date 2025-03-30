package com.example.moneyusage.frontend.screens.home_screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.example.moneyusage.frontend.helper.Period

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopContents(scrollBehavior: TopAppBarScrollBehavior, periodState: MutableState<Period>) {

    MediumTopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.background
        ),
        title = { TopAppBarTitle(periodState) },
        navigationIcon = { TopAppBarNavigationIcon() },
        actions = { TopAppBarActions() },
        scrollBehavior = scrollBehavior,
    )
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun TopAppBarTitle(periodState: MutableState<Period>) {
    val styles = Styles()

    val topBarTextColor = if (isSystemInDarkTheme()) styles.darkButtonTextColor
    else styles.buttonTextColor

    val primaryThemeColor = colorResource(id = R.color.primaryThemeColor)
    val secondaryThemeColor = colorResource(id = R.color.secondaryThemeColor)
    val materialColor = MaterialTheme.colorScheme.onBackground

    // Handle background changes on click
    val selectedButtonBackground: (label: Period) -> TopAppButtonColors = {
        val bgColor = if (periodState.value == it) primaryThemeColor
        else secondaryThemeColor
        val textColor = if (periodState.value == it) primaryThemeColor
        else materialColor
        TopAppButtonColors(bgColor, textColor)
    }

    val topAppButtonArgs = listOf(
        // All arguments
        TopAppButtonArgs(
            Period.Today,
            topBarTextColor = topBarTextColor,
            selectedButton = periodState,
            onClick = {
            }
        ),

        // Today argument
        TopAppButtonArgs(
            Period.Yesterday,
            topBarTextColor = topBarTextColor,
            selectedButton = periodState,
            onClick = {
            }
        ),

        // Today argument
        TopAppButtonArgs(
            Period.Weekly,
            topBarTextColor = topBarTextColor,
            selectedButton = periodState,
            onClick = {
            }
        ),

        // Yesterday argument
        TopAppButtonArgs(
            Period.All,
            topBarTextColor = topBarTextColor,
            selectedButton = periodState,
            onClick = {
            }
        ),
    )

        LazyRow (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(end = 10.dp)
        ) {

            items(topAppButtonArgs.size){
                topAppButtonArgs[it].also {
                    arg ->
                    TopAppButton(
                        topAppButtonArgs = arg,
                        selectedButtonBackground = selectedButtonBackground
                    )
                }
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