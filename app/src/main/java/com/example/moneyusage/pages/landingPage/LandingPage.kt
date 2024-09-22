package com.example.moneyusage.pages.landingPage

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import co.yml.charts.common.model.Point
import com.example.moneyusage.R
import com.example.moneyusage.charts.LineChart
import com.example.moneyusage.components.BottomIconButton
import com.example.moneyusage.components.CurrentBalanceCard
import com.example.moneyusage.components.DialogAlert
import com.example.moneyusage.components.FloatActionButton
import com.example.moneyusage.components.RecentTransactions
import com.example.moneyusage.components.TopAppButton
import com.example.moneyusage.dataclasses.BottomIcon
import com.example.moneyusage.dataclasses.DialogAmountState
import com.example.moneyusage.dataclasses.FloatActionButtonData
import com.example.moneyusage.dataclasses.PieChartData
import com.example.moneyusage.dataclasses.Styles
import com.example.moneyusage.dataclasses.TopAppButtonArgs
import com.example.moneyusage.dataclasses.TopAppButtonColors
import com.example.moneyusage.helper.WeekDays.Fri
import com.example.moneyusage.helper.WeekDays.Mon
import com.example.moneyusage.helper.WeekDays.Sat
import com.example.moneyusage.helper.WeekDays.Sun
import com.example.moneyusage.helper.WeekDays.Thu
import com.example.moneyusage.helper.WeekDays.Tue
import com.example.moneyusage.helper.WeekDays.Wed

class LandingPage {
    private val styles = Styles()


    @RequiresApi(Build.VERSION_CODES.P)
    @Composable
    fun TopAppBarTitle() {
        val topBarTextColor = if (isSystemInDarkTheme()) styles.darkButtonTextColor
        else styles.buttonTextColor

        val selectedButton = remember {
            mutableStateOf("All")
        }

        val primaryThemeColor: Color = colorResource(id = R.color.primaryThemeColor)
        val secondaryThemeColor: Color = colorResource(id = R.color.secondaryThemeColor)

        // Handle background changes on click
        val selectedButtonBackground: (label: String) -> TopAppButtonColors = {
            val bgColor = if (selectedButton.value == it) primaryThemeColor
            else styles.buttonBackgroundColor
            val textColor = if (selectedButton.value == it) Color.White
            else secondaryThemeColor
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
                onClick = { /*TODO*/ }
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
        // Handle light and dark icon colors
//        val color = if (isSystemInDarkTheme()) Color.White
//        else Color.Yellow

        // Icon
        val icon = if (isSystemInDarkTheme()) R.drawable.moon
        else R.drawable.sun

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                modifier = Modifier.size(25.dp),
                painter = painterResource(id = icon),
//                tint = color,
                contentDescription = "light mode"
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun TopBar() {

        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            ),
            title = { TopAppBarTitle() },
            navigationIcon = { TopAppBarNavigationIcon() },
            actions = { TopAppBarActions() }
        )
    }

    @SuppressLint("UnrememberedMutableState")
    @Composable
    fun BottomBar(
        floatActionButtonClickState: MutableState<Boolean>,
        lazyState: LazyListState
    ) {

        val state = remember { derivedStateOf { lazyState.firstVisibleItemScrollOffset == 0 } }
        val selectState = remember { mutableStateOf("Home") }
        val floatActionButtonAppearanceState = remember { mutableStateOf("") }

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

                FloatActionButton(
                    floatActionButtonData = FloatActionButtonData(
                        containerOpenColor = colorResource(
                            id = R.color.primaryThemeColor
                        ),
                        containerCloseColor = colorResource(
                            id = R.color.secondaryThemeColor
                        ),
                        label = "Add",
                        openIcon = R.drawable.add,
                        closeIcon = R.drawable.close,

                        onClick = {
                            floatActionButtonClickState.value = !floatActionButtonClickState.value
                        }
                    ),
                    appearanceState = floatActionButtonAppearanceState
                )

                if (floatActionButtonClickState.value) {
                    floatActionButtonAppearanceState.value = "Add"
                } else {
                    floatActionButtonAppearanceState.value = ""
                }
            }
        )
    }

    @Composable
    fun SnackBarHost() {

    }

    @Composable
    fun FloatingAction(
        innerFloatActionButtonAppearanceState: MutableState<String>,
        mainFloatActionButtonClickState: MutableState<Boolean>,
    ) {

        val floatActionButton = listOf(
            // Income floating action button dataclass
            FloatActionButtonData(
                containerOpenColor = colorResource(
                    id = R.color.incomeCloseColor
                ),
                openIcon = R.drawable.outline_income,
                closeIcon = R.drawable.filled_income,
                containerCloseColor = colorResource(
                    id = R.color.incomeOpenColor
                ),
                label = "Income",
                onClick = {
                }
            ),

            // Expense floating action button dataclass
            FloatActionButtonData(
                containerOpenColor = colorResource(
                    id = R.color.expenseCloseColor
                ),
                containerCloseColor = colorResource(
                    id = R.color.expenseOpenColor
                ),
                openIcon = R.drawable.outline_expense,
                closeIcon = R.drawable.filled_expense,
                label = "Expense",
                onClick = {
                }
            ),

            // Debt floating action button dataclass
            FloatActionButtonData(
                containerOpenColor = colorResource(
                    id = R.color.debtCloseColor
                ),
                containerCloseColor = colorResource(
                    id = R.color.debtOpenColor
                ),
                openIcon = R.drawable.outline_debt,
                closeIcon = R.drawable.filled_debt,
                label = "Debt",
                onClick = {
                }
            ),

            // Lend floating action button dataclass
            FloatActionButtonData(
                containerOpenColor = colorResource(
                    id = R.color.lendCloseColor
                ),
                containerCloseColor = colorResource(
                    id = R.color.lendOpenColor
                ),
                openIcon = R.drawable.outline_lend,
                closeIcon = R.drawable.filled_lend,
                label = "Lend",
                onClick = {
                }
            ),
        )

        if (mainFloatActionButtonClickState.value) {
            Column(
                modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        mainFloatActionButtonClickState.value = false
                    })
                }
            ) {
                    floatActionButton.forEach {
                        FloatActionButton(
                            floatActionButtonData = it,
                            appearanceState = innerFloatActionButtonAppearanceState
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
            }
        }
    }

    /**
     * Profile Detail Drawer
     */
    @Composable
    fun ProfileDetailDrawer() {
    }

    @RequiresApi(Build.VERSION_CODES.P)
    @Composable
    fun Content(
        innerPadding: PaddingValues,
        state: LazyListState
    ) {

        val currentIncomeAndExp = listOf(
            PieChartData(
                "Income", 200230.990,
                color = colorResource(id = R.color.incomeCloseColor)
            ),
            PieChartData(
                "Expense", 19800_0967.9887,
                color = colorResource(id = R.color.expenseCloseColor)
            ),
        )

        val debtAndLend = listOf(
            PieChartData(
                "Debt", 989.00,
                color = colorResource(id = R.color.debtCloseColor)
            ),
            PieChartData(
                "Lend", 3.000000000000988E12,
                color = colorResource(id = R.color.lendCloseColor)
            ),
        )

        // Current balance card size
        val currentBalanceCardSize = 150.dp

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            state = state
        ) {
            item {
                Spacer(modifier = Modifier.size(50.dp))
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
                        "Current balance",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(450)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyRow(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    item {
                        Spacer(modifier = Modifier.width(10.dp))
                        CurrentBalanceCard(
                            data = currentIncomeAndExp,
                            size = currentBalanceCardSize
                        ) {

                        }
                    }

                    item {
                        Spacer(modifier = Modifier.width(20.dp))
                    }

                    item {
                        CurrentBalanceCard(
                            data = debtAndLend,
                            size = currentBalanceCardSize
                        ) {

                        }
                    }
                }
            }

            // Line chart Item
            item {
                Spacer(modifier = Modifier.height(20.dp))

                // Sub Title
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp)
                ) {
                    Text(
                        "Timeline chart",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(450)
                    )
                }

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    val pointsData: List<Point> =
                        listOf(
                            Point(0f, 0f),
                            Point(1f, 90f),
                            Point(2f, 80f),
                            Point(3f, 80f),
                            Point(4f, 30f),
                            Point(5f, 23f),
                            Point(6f, 53f),
                            Point(7f, 69f),
                        )

                    val data: List<Point> =
                        listOf(
                            Point(0f, 0f),
                            Point(1f, 32f),
                            Point(2f, 56f),
                            Point(3f, 87f),
                            Point(4f, 25f),
                            Point(5f, 23f),
                            Point(6f, 73f),
                            Point(7f, 49f),
                        )

                    LineChart(
                        data = pointsData,
                        secondData = data,
                        labelData = {
                            when (it) {
                                0 -> ""
                                3 -> Mon.name
                                4 -> Tue.name
                                5 -> Wed.name
                                else -> ""
                            }
                        },

                        mapPopUpLabel = { x, y ->
                            val xLabel = when (x.toInt()) {
                                1 -> Mon
                                2 -> Tue
                                3 -> Wed
                                4 -> Thu
                                5 -> Fri
                                6 -> Sat
                                7 -> Sun
                                else -> ""
                            }
                            "$xLabel : $y"
                        },
                    )
                }
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
                RecentTransactions()

                Spacer(modifier = Modifier.height(100.dp))


            }
        }
    }


    // --------- LandPage -----------
    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun LandPage() {
        // Lazy list state
        val listState = rememberLazyListState()

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
                .fillMaxSize(),
            topBar = { TopBar() },
            bottomBar = {
                BottomBar(
                    lazyState = listState,
                    floatActionButtonClickState = mainFloatActionButtonClickState
                )
            },
            snackbarHost = { SnackBarHost() },
            containerColor = MaterialTheme.colorScheme.background,
            floatingActionButton = {
                FloatingAction(
                    innerFloatActionButtonAppearanceState = innerFloatActionButtonAppearanceState,
                    mainFloatActionButtonClickState = mainFloatActionButtonClickState,
                )
            },
            floatingActionButtonPosition = FabPosition.End,
        ) { innerPadding ->

            // Introduces the contents
            Content(
                innerPadding = innerPadding,
                state = listState
            )
        }

        // Handle Dialog for floating action button
        DialogAlert(
            state = innerFloatActionButtonAppearanceState,
            dialogAmountState = dialogAmountState
        )
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("UnrememberedMutableState")
@Preview(
    showBackground = true,
    device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420",
    showSystemUi = true, name = "LandPagePreview"
)
@Composable
fun LandPagePreview() {
    LandingPage().LandPage()
}