package com.example.moneyusage.pages.landingPage

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyusage.R
import com.example.moneyusage.components.CurrentBalanceCard
import com.example.moneyusage.dataclasses.PieData

class LandingPage {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBar() {
        val iconColor = Color(
            241,
            237,
            237,
            105
        )

        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),

            title = {
                FilledIconButton(
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = iconColor),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "d",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(9, 8, 8, 178),
                        fontFamily = FontFamily(Font(resId = R.font.comfortaa))
                    )
                }
            },

            navigationIcon = {

            },
            actions = {
                FilledIconButton(
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = iconColor),
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(100.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .padding(2.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "stat",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(resId = R.font.comfortaa))
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.line_chart),
                            contentDescription = "line_chart",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                FilledIconButton(
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = iconColor),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "MoreVert")
                }
            }

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
    fun Content() {

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.size(90.dp))
            }

            item {
                val currentIncomeAndExp = listOf(
                    PieData("Income", 200230.990,
                        color = colorResource(id=R.color.income)),
                    PieData("Expense", 19800_0967.9887,
                        color = colorResource(id=R.color.expend)),
                )

                val debtAndLend = listOf(
                    PieData("Debt", 989.00,
                        color = colorResource(id=R.color.debt)),
                    PieData("Lend", 3.000000000000988E12,
                        color = colorResource(id=R.color.lend)),
                )

                Row(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    /**
                     * Current Income and Expense Card
                     */
                    CurrentBalanceCard(data =currentIncomeAndExp) {

                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    /**
                     * Current Debt and Loan Card
                     */
                    CurrentBalanceCard(data =debtAndLend, size = 210.dp) {

                    }
                }
            }
        }
    }


    // --------- LandPage -----------
    @RequiresApi(Build.VERSION_CODES.P)
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun LandPage() {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

        Scaffold(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = { TopBar() },
            bottomBar = { BottomBar() },
            snackbarHost = { SnackBarHost() },
            floatingActionButton = { FloatingActionButton() },
            floatingActionButtonPosition = FabPosition.End,
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            Content()
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