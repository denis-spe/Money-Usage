package com.example.moneyusage.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.moneyusage.R
import com.example.moneyusage.dataclasses.AmountButtonState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.last

@Composable
fun AmountButton(
    amountTextField: MutableState<TextFieldValue>,
    amountButtonState: MutableState<AmountButtonState>,
    icon: Int,
    buttonColor: Int,
    waitingListener: () -> Unit
){

    LaunchedEffect(key1 = amountButtonState.value) {
        if (amountButtonState.value == AmountButtonState.LOADING) {
            delay(2000)
            waitingListener()
        }
    }

    val button = when (amountButtonState.value) {
        AmountButtonState.FINISHED -> Pair(R.drawable.finished, buttonColor)
        AmountButtonState.INITIAL -> Pair(icon, buttonColor)
        AmountButtonState.ERROR -> Pair(R.drawable.error, R.color.expenseCloseColor)
        AmountButtonState.LOADING -> Pair(R.drawable.loading, buttonColor)
        AmountButtonState.INSERTDATA -> Pair(R.drawable.unavaliable, R.color.expenseOpenColor)
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
        ) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                FilledIconButton(
                    onClick = {
                        if (amountButtonState.value == AmountButtonState.INITIAL
                            && amountTextField.value.text.isNotEmpty()) {
                            amountButtonState.value = AmountButtonState.LOADING
                        }

                        if (amountTextField.value.text.isEmpty()) {
                            amountButtonState.value = AmountButtonState.INSERTDATA
                        }
                    },
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = colorResource(id = button.second),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        painter = painterResource(
                            id = button.first
                        ),
                        contentDescription = "Deposit"
                    )
                }
            }
            
            if (amountButtonState.value == AmountButtonState.LOADING) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(70.dp),
                        color = colorResource(id = buttonColor),
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }
            }
        }
    }
}