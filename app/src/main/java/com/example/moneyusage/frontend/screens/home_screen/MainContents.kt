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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.moneyusage.backend.models.Data
import com.example.moneyusage.frontend.helper.Period

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MainContents(
    innerPadding: PaddingValues,
    state: LazyListState,
    dataset: State<List<Data>>,
    periodState: MutableState<Period>
) {
    when (periodState.value) {
        Period.Today -> Today(
            lazyListState = state,
            innerPadding = innerPadding,
            dataset = dataset
        )

        Period.Yesterday -> Yesterday(
            lazyListState = state,
            innerPadding = innerPadding,
            dataset = dataset
        )

        Period.Weekly -> Weekly(
            lazyListState = state,
            innerPadding = innerPadding,
            dataset = dataset
        )

        Period.All -> All(
            lazyListState = state,
            innerPadding = innerPadding,
            dataset = dataset
        )
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun Today(
    lazyListState: LazyListState,
    innerPadding: PaddingValues,
    dataset: State<List<Data>>
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        state = lazyListState
    ) {
        item {
            TodayPeriodPieChart(
                dataset = dataset
            )
        }

        item {
            TodaySectionTimeLine(
                dataset = dataset
            )
        }
    }
}

@Composable
fun Yesterday(
    lazyListState: LazyListState,
    innerPadding: PaddingValues,
    dataset: State<List<Data>>
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        state = lazyListState
    ) {
        // TODO
    }
}

@Composable
fun Weekly(
    lazyListState: LazyListState,
    innerPadding: PaddingValues,
    dataset: State<List<Data>>
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        state = lazyListState
    ) {
        // TODO
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun All(
    lazyListState: LazyListState,
    innerPadding: PaddingValues,
    dataset: State<List<Data>>
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        state = lazyListState
    ) {

        // Current Amount section
        item {
            CurrentAmountSection(dataset = dataset)
        }

        // Pie chart section
        item {
            AllPeriodPieChart(dataset = dataset)
        }

        // Time Line section
        item {
            AllSectionTimeLine(dataset = dataset)
        }

        item {
            // Recent Transactions
            RecentTransactions(dataset = dataset)
        }
    }
}