package com.example.moneyusage.frontend.screens.home_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MainContents(
    viewModel: HomeScreenViewModel,
    innerPadding: PaddingValues,
    state: LazyListState,
    restartApp: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.initialize(restartApp)
    }

    // Collect dataset as state
    val dataset = viewModel.database.collectAsState(initial = emptyList())
    viewModel.fetchData()

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        state = state
    ) {

        // Current Amount section
        item {
            CurrentAmountSection(dataset = dataset)
        }

        // Pie chart section
        item {
            PieChartSection(dataset = dataset)
        }

        // Time Line section
        item {
            TimeLineSection(dataset = dataset)
        }

        item {
            // Recent Transactions
            RecentTransactions(dataset = dataset)
        }
    }
}