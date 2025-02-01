package com.example.moneyusage.frontend.screens.home_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.example.moneyusage.backend.models.services.impl.AccountServiceImpl
import com.example.moneyusage.backend.models.services.impl.StorageServiceImpl

// --------- LandPage -----------
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = HomeScreenViewModel(
        accountService = AccountServiceImpl(),
        storageService = StorageServiceImpl(AccountServiceImpl())
    ),
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.initialize(restartApp)
    }

    // Collect dataset as state
    val dataset = viewModel.database.collectAsState(initial = emptyList())
    viewModel.fetchData()

    // Lazy list state
    val listState = rememberLazyListState()

    // Open dialog state
    val onOpenDialogState = remember { mutableStateOf(false) }

    // Main floating action button state
    val mainFloatActionButtonClickState = remember { mutableStateOf(false) }

    // Inner floating action button state
    val onOpenUpdateDialogState = remember { mutableStateOf(false) }

    // Snack bar state
    val snackBarHostState = remember { SnackbarHostState() }
    // Coroutine scope
    val scope = rememberCoroutineScope()

    // Bottom sheet state
    val sheetState = rememberModalBottomSheetState()
    // Bottom sheet state
    val showBottomSheet = remember { mutableStateOf(false) }

    // Scaffold
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit){
                detectTapGestures {
                    mainFloatActionButtonClickState.value = false
                }
            },
        topBar = { TopContents() },
        bottomBar = {
            BottomContents(
                lazyState = listState,
                onUpdateFloatActionButtonState = onOpenUpdateDialogState,
                showBottomSheet = showBottomSheet,
                viewModel = viewModel,
                dataset = dataset,
                scope = scope,
                snackBarHostState = snackBarHostState,
                sheetState = sheetState
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
            )
        },
        floatingActionButton = {
            FloatActionButton(
                onOpenDialogState = onOpenDialogState,
            )
        },
        containerColor = MaterialTheme.colorScheme.background,

    ) { innerPadding ->

        // Main contents
        MainContents(
            viewModel = viewModel,
            innerPadding = innerPadding,
            state = listState,
            dataset = dataset,
            restartApp = restartApp
        )
    }


    // Handle Dialog for floating action button
    DialogAlertContents(
        onOpenDialogState = onOpenDialogState,
        snackBarHostState = snackBarHostState,
        scope = scope,
        viewModel = viewModel
    )
}


@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Preview(
    showBackground = true,
    device = "spec:width=411dp,height=891dp,dpi=420",
    showSystemUi = true, name = "LandPagePreview"
)
@Composable
fun LandPagePreview() {
    HomeScreen(restartApp = {}){
            _ ->
    }
}