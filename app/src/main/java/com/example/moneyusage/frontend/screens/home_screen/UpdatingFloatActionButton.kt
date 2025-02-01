package com.example.moneyusage.frontend.screens.home_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyusage.DEBT
import com.example.moneyusage.LENT
import com.example.moneyusage.R
import com.example.moneyusage.backend.models.Data
import com.example.moneyusage.frontend.components.AmountInputField
import com.example.moneyusage.frontend.components.DropDownComponent
import com.example.moneyusage.frontend.dataclasses.AmountButtonState
import com.example.moneyusage.frontend.helper.clearUpCommas
import com.example.moneyusage.frontend.helper.toMoneyFormat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatingFloatActionButton(
    state: MutableState<Boolean>,
    showBottomSheet: MutableState<Boolean>,
) {
    val primaryThemeColor = colorResource(id = R.color.primaryThemeColor)

    var rotation by remember { mutableFloatStateOf(0f) }

    // Animate rotation when the value of `rotation` changes
    val animatedRotation by animateFloatAsState(
        targetValue = rotation,
        animationSpec = tween(durationMillis = 1000),
        label = "",
    )

    FloatingActionButton(
        onClick = {
            rotation += 360f
            showBottomSheet.value = true
        },
        modifier = Modifier.size(45.dp),
        containerColor = primaryThemeColor,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(10.dp),
        shape = CircleShape,
    ) {
        Icon(
            modifier = Modifier.size(25.dp)
                .graphicsLayer(rotationZ = animatedRotation),
            imageVector = if (state.value) Icons.Default.Close
            else Icons.Default.Update,
            tint = Color.White,
            contentDescription = "Update"
        )
    }
}

@Composable
fun ModalBottomSheetContent(
    viewModel: HomeScreenViewModel,
    showBottomSheet: MutableState<Boolean>,
    dataset: State<List<Data>>,
    scope: CoroutineScope,
    snackBarHostState: SnackbarHostState
) {
    // Drop down state
    val selectedTransaction = remember { mutableStateOf(TextFieldValue("")) }
    val selectCategory = remember { mutableStateOf(TextFieldValue("")) }
    val selectedData = remember { mutableStateOf(Data()) }

    // Amount input state
    val amountState = remember { mutableStateOf(TextFieldValue("")) }
    val amountButtonState = remember { mutableStateOf(AmountButtonState.INITIAL) }
    val amountPlaceholder = remember { mutableStateOf("0.00") }

    LaunchedEffect(selectCategory.value) {
        selectedTransaction.value = TextFieldValue("")
    }

    if (selectedData.value.amount != 0.0){
        amountPlaceholder.value = selectedData.value.amount.toMoneyFormat()
    }

    val debts = dataset.value.filter { it.category == DEBT }
    val lent = dataset.value.filter { it.category == LENT }

    val items = when (selectCategory.value.text) {
        DEBT -> {
            debts
        }
        LENT -> {
            lent
        }
        else -> {
            emptyList()
        }
    }


    val spacerHeight = 15.dp

    Column(
        modifier = Modifier
            .padding(start = 50.dp, end = 50.dp)
    ) {

        Spacer(Modifier.height(spacerHeight))

        Text(
            text = "Repaying the transaction",
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 10.dp, start = 20.dp)
        )

        Spacer(Modifier.height(spacerHeight))

        DropDownComponent(
            label = "Select Category",
            placeHolder = "category",
            items = listOf(DEBT, LENT),
            selectedText = selectCategory,
            showTailingBtn = false
        )

        Spacer(Modifier.height(spacerHeight))

        DropDownComponent(
            label = "Select Transaction",
            placeHolder = "transaction",
            items = items,
            selectedText = selectedTransaction,
            selectedData = selectedData,
            readOnly = true
        )

        Spacer(Modifier.height(spacerHeight))

        AmountInputField(
            amountButtonState = amountButtonState,
            state = amountState,
            placeHolder = amountPlaceholder,
        )

        Spacer(Modifier.height(spacerHeight))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            OutlinedButton(
                onClick = {
                    scope.launch {
                        if (amountState.value.text.isNotEmpty()) {
                            val amount = amountState.value.text.clearUpCommas
                            val currentAmount = selectedData.value.amount

                            if (amount == selectedData.value.amount) {
                                viewModel.deleteData(selectedData.value.dataId)
                            } else {
                                if (currentAmount > amount) {
                                    viewModel.updateData(
                                        selectedData.value.copy(amount = currentAmount - amount)
                                    )
                                    when(selectCategory.value.text) {
                                        DEBT -> snackBarHostState.showSnackbar(
                                            "Successfully paid")

                                        LENT -> snackBarHostState.showSnackbar(
                                            "Successfully repaid")
                                    }
                                }
                                else {
                                    snackBarHostState.showSnackbar(
                                        "Exceeded amount for " +
                                                "${selectedTransaction.value.text} transaction.",
                                        actionLabel = "close"
                                        )
                                }
                            }
                        }
                    }
                    showBottomSheet.value = false
                }
            ) {
                Text(
                    text = "Repay",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                )
            }
        }

        Spacer(Modifier.height(spacerHeight))
    }
}