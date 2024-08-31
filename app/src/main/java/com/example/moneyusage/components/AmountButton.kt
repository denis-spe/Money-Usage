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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.moneyusage.R

@Composable
fun AmountButton(
    textFieldState: MutableState<TextFieldValue>,
    waitingListener: () -> Unit
){
    val buttonState = remember {
        mutableStateOf(false)
    }

    val finishedState = remember {
        mutableStateOf(false)
    }

    val loadingState = remember {
        mutableStateOf(false)
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                if (loadingState.value) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(60.dp),
                        color = colorResource(id = R.color.incomeOpenColor),
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }
            }
            
            if (loadingState.value) {
                waitingListener()
                finishedState.value = true
                loadingState.value = false
            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                FilledIconButton(
                    onClick = {
                        loadingState.value = !loadingState.value
                    },
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = colorResource(id = R.color.incomeCloseColor),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.size(35.dp)
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (finishedState.value)
                                R.drawable.finished
                            else R.drawable.deposit
                        ),
                        contentDescription = "Deposit"
                    )
                }
            }
        }
    }
}