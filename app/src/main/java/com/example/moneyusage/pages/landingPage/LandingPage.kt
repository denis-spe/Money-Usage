package com.example.moneyusage.pages.landingPage

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.Point
import com.example.moneyusage.R
import com.example.moneyusage.charts.AnimatedPieChart
import com.example.moneyusage.charts.LineChartComponent
import com.example.moneyusage.components.CurrentBalanceCard
import com.example.moneyusage.dataclasses.PieData

class LandingPage {

    @RequiresApi(Build.VERSION_CODES.P)
    @Composable
    fun TopAppBarTitle() {
        val buttonBackgroundColor = Color.Gray.copy(alpha = 0.4f)
        val clickedButtonColor = Color.Green.copy(alpha = 0.7f)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Spacer(modifier = Modifier.width(5.dp))

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = clickedButtonColor,
                )
            ) {
                Text("All",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Spacer(modifier = Modifier.width(5.dp))

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonBackgroundColor,
                )
            ) {
                Text("Today",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Spacer(modifier = Modifier.width(5.dp))

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonBackgroundColor,
                )
            ) {
                Text("Yesterday",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    @Composable
    fun TopAppBarNavigationIcon(){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(start = 10.dp)
        ) {
            FilledIconButton(
                modifier = Modifier
                    .size(30.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = Color.Magenta.copy(alpha = 0.7f, red = 0.9f)),
                onClick = { /*TODO*/ }
            ) {

                Text(
                    text = "D",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                )
            }
        }
    }

    @Composable
    fun TopAppBarActions() {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                modifier = Modifier.size(25.dp),
                painter = painterResource(id = if (isSystemInDarkTheme())
                    R.drawable.moon else R.drawable.sun
                ),
                contentDescription = "light mode")
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun TopBar() {

        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(),
            title = { TopAppBarTitle() },
            navigationIcon = { TopAppBarNavigationIcon() },
            actions = { TopAppBarActions() }
        )
    }

    @Composable
    fun BottomBar() {
        BottomAppBar(actions = {

        })
    }

    @Composable
    fun SnackBarHost() {

    }

    @Composable
    fun FloatingActionButton() {

    }

    /**
     * Profile Detail Drawer
     */
    @Composable
    fun ProfileDetailDrawer() {
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.P)
    @Composable
    fun Content(
        innerPadding: PaddingValues
    ) {

        val currentIncomeAndExp = listOf(
            PieData(
                "Income", 200230.990,
                color = colorResource(id = R.color.income)
            ),
            PieData(
                "Expense", 19800_0967.9887,
                color = colorResource(id = R.color.expend)
            ),
        )

        val debtAndLend = listOf(
            PieData(
                "Debt", 989.00,
                color = colorResource(id = R.color.debt)
            ),
            PieData(
                "Lend", 3.000000000000988E12,
                color = colorResource(id = R.color.lend)
            ),
        )

        // Current balance card size
        val currentBalanceCardSize = 150.dp

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
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
                        .padding(start = 20.dp)
                ) {
                    Text("Current balance",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyRow (
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    item {
                        Spacer(modifier = Modifier.width(20.dp))
                    }

                    item {
                        CurrentBalanceCard(
                            data = currentIncomeAndExp,
                            size = currentBalanceCardSize
                        ){

                        }
                    }

                    item {
                        Spacer(modifier = Modifier.width(20.dp))
                    }

                    item {
                        CurrentBalanceCard(
                            data = debtAndLend,
                            size = currentBalanceCardSize
                        ){

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
                        .padding(start = 20.dp)
                ) {
                    Text("Timeline chart",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(modifier = Modifier.padding(20.dp)
                    ) {
                    val pointsData: List<Point> =
                        listOf(
                            Point(0f, 0f),
                            Point(1f, 90f),
                            Point(2f, 80f),
                            Point(3f, 50f),
                            Point(4f, 30f),
                            Point(5f, 23f),
                            Point(6f, 53f),
                            Point(7f, 69f),
                        )
                    LineChartComponent(
                        data = pointsData,
                        labelData = {
                            when(it) {
                                0 -> ""
                                3 -> "Wed"
                                4 -> "Thu"
                                5 -> "Fri"
                                else -> ""
                            }
                        },
                        mapPopUpLabel = {
                            x, y ->
                            val xLabel = when(x.toInt()) {
                                1 -> "Mon"
                                2 -> "Tue"
                                3 -> "Wed"
                                4 -> "Thu"
                                5 -> "Fri"
                                6 -> "Sat"
                                7 -> "Sun"
                                else -> ""
                            }
                            "$xLabel : $y"
                        }
                    )
                }
            }
        }
    }


    // --------- LandPage -----------
    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun LandPage() {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = { TopBar() },
            bottomBar = { BottomBar() },
            snackbarHost = { SnackBarHost() },
            floatingActionButton = { FloatingActionButton() },
            floatingActionButtonPosition = FabPosition.End,
        ) { innerPadding ->
            Content(innerPadding)
        }
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