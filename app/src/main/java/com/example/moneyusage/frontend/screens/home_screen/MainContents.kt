package com.example.moneyusage.frontend.screens.home_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
    val isLoading = viewModel.isLoading.collectAsState()
    viewModel.fetchData()

    if (isLoading.value) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }

    if (!isLoading.value) {
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
                // Sub Title
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                ) {
                    Text(
                        "Recent Transactions",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(450)
                    )
                }


                // Recent Transactions
                RecentTransactions(dataset = dataset)

                Spacer(modifier = Modifier.height(100.dp))


            }
        }
    }
}