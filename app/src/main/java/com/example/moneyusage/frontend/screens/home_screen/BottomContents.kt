package com.example.moneyusage.frontend.screens.home_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.moneyusage.R
import com.example.moneyusage.backend.models.Data
import com.example.moneyusage.frontend.dataclasses.BottomIcon
import com.example.moneyusage.frontend.dataclasses.Styles
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun BottomContents(
    lazyState: LazyListState,
    onUpdateFloatActionButtonState: MutableState<Boolean>,
    viewModel: HomeScreenViewModel,
    dataset: State<List<Data>>,
    scope: CoroutineScope,
    snackBarHostState: SnackbarHostState,
    showBottomSheet: MutableState<Boolean>,
    sheetState: SheetState,
) {

    val styles = Styles()

    val state = remember { derivedStateOf { lazyState.firstVisibleItemScrollOffset == 0 } }
    val selectState = remember { mutableStateOf("Home") }

    val bottomIcons = listOf(
        // Bottom Home Icon
        BottomIcon(
            outlineIcon = painterResource(id = R.drawable.outline_home),
            filledIcon = painterResource(id = R.drawable.filled_home),
            description = "Home",
            state = state,
            animateDelayMillis = 500,
            onClick = { /*TODO*/ }
        ),

        // Second Bottom Icon
        BottomIcon(
            outlineIcon = painterResource(id = R.drawable.outline_search),
            filledIcon = painterResource(id = R.drawable.filled_search),
            description = "Search",
            state = state,
            animateDelayMillis = 700,
            onClick = { /*TODO*/ }
        ),

        // History Bottom Icon
        BottomIcon(
            outlineIcon = painterResource(id = R.drawable.outline_history),
            filledIcon = painterResource(id = R.drawable.filled_history),
            description = "History",
            state = state,
            animateDelayMillis = 1000,
            onClick = { /*TODO*/ }
        )
    )

    BottomAppBar(
        modifier = Modifier,
        containerColor = styles.bottomAppBarBackgroundColor,
        tonalElevation = 0.dp,
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 10.dp)
            ) {
                bottomIcons.forEach {
                    BottomIconButton(
                        buttonIcon = it,
                        selectState = selectState
                    )
                }
            }
        },
        floatingActionButton = {
            UpdatingFloatActionButton(
                showBottomSheet = showBottomSheet,
                state = onUpdateFloatActionButtonState
            )
        },
        contentPadding = PaddingValues(end = 8.dp)
    )

    if (showBottomSheet.value) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet.value = false
            },
            sheetState = sheetState,
            containerColor = BottomSheetDefaults.ContainerColor.copy(0.95f)
        ) {
            ModalBottomSheetContent(
                dataset = dataset,
                viewModel = viewModel,
                scope = scope,
                snackBarHostState = snackBarHostState,
                showBottomSheet = showBottomSheet
            )
        }
    }
}