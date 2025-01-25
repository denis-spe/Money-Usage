package com.example.moneyusage.frontend.screens.home_screen

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import com.example.moneyusage.SPLASH_SCREEN
import com.example.moneyusage.backend.models.Data
import com.example.moneyusage.backend.models.services.impl.StorageServiceImpl
import com.example.moneyusage.backend.models.services.interf.AccountService
import com.example.moneyusage.frontend.dataclasses.DateTime
import com.example.moneyusage.frontend.helper.PaymentStatus
import com.example.moneyusage.frontend.screens.AppViewModel
import javax.inject.Inject

class HomeScreenViewModel@Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageServiceImpl
): AppViewModel() {

    // Storage service
    val database = storageService.dataset

    fun onDateSaveClick(
        category: MutableState<TextFieldValue>,
        amount: MutableState<TextFieldValue>,
        description: MutableState<TextFieldValue>,
        date: DateTime,
        icon: Int,
        paymentStatus: PaymentStatus? = null,
        lentTo: MutableState<TextFieldValue>? = null,
        debtFrom: MutableState<TextFieldValue>? = null
    ) {
        launchCatching {
            storageService.addData(Data(
                userId = accountService.currentUserId,
                category = category.value.text,
                amount = amount.value.text
                    .replace(",", "")
                    .toDouble(),
                description = description.value.text,
                date = date,
                icon = icon,
                paymentStatus = paymentStatus,
                lentTo = lentTo?.value?.text,
                debtFrom = debtFrom?.value?.text
            ))
        }

        category.value = TextFieldValue("")
        amount.value = TextFieldValue("")
        description.value = TextFieldValue("")
        debtFrom?.value = TextFieldValue("")
        lentTo?.value = TextFieldValue("")
    }

    fun fetchData() {
        storageService.fetchData()
    }

    fun initialize(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.currentUser.collect { user ->
                if (user == null) {
                    restartApp(SPLASH_SCREEN)
                }
            }
        }
    }
}