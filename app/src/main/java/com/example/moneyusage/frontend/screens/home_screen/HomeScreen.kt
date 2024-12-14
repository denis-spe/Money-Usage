package com.example.moneyusage.frontend.screens.home_screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.moneyusage.DEBT
import com.example.moneyusage.EXPENSE
import com.example.moneyusage.INCOME
import com.example.moneyusage.LENT
import com.example.moneyusage.backend.models.services.impl.AccountServiceImpl
import com.example.moneyusage.backend.models.services.impl.StorageServiceImpl
import com.example.moneyusage.frontend.dataclasses.DialogAlertBtnListener
import com.example.moneyusage.frontend.dataclasses.DialogAmountState
import com.example.moneyusage.frontend.helper.changeToDouble

// --------- LandPage -----------
@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "NotConstructor")
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = HomeScreenViewModel(
        accountService = AccountServiceImpl(),
        storageService = StorageServiceImpl(AccountServiceImpl())
    ),
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit
) {

    // Lazy list state
    val listState = rememberLazyListState()

    // Open dialog state
    val onOpenDialogState = remember { mutableStateOf(false) }

    // Main floating action button state
    val mainFloatActionButtonClickState = remember { mutableStateOf(false) }

    // Inner floating action button state
    val innerFloatActionButtonAppearanceState = remember { mutableStateOf("") }

    // Dialog state
    val dialogAmountState = DialogAmountState(
        // Income states
        income = remember {
            mutableStateOf(TextFieldValue(""))
        },
        incomeDesc = remember {
            mutableStateOf(TextFieldValue(""))
        },

        // Expense states
        expense = remember {
            mutableStateOf(TextFieldValue(""))
        },
        expenseDesc = remember {
            mutableStateOf(TextFieldValue(""))
        },

        // Debt states
        debt = remember {
            mutableStateOf(TextFieldValue(""))
        },
        debtDesc = remember {
            mutableStateOf(TextFieldValue(""))
        },

        // Lend states
        lend = remember {
            mutableStateOf(TextFieldValue(""))
        },
        lendDesc = remember {
            mutableStateOf(TextFieldValue(""))
        }
    )

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
                onOpenDialogState = onOpenDialogState
            )
        },
        snackbarHost = {  },
        containerColor = MaterialTheme.colorScheme.background,

    ) { innerPadding ->

        // Main contents
        MainContents(
            viewModel = viewModel,
            innerPadding = innerPadding,
            state = listState,
            restartApp = restartApp
        )
    }


    // Handle Dialog for floating action button
    DialogAlertContents(
        onOpenDialogState = onOpenDialogState,
        viewModel = viewModel
    )
}

@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("UnrememberedMutableState")
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