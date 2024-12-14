package com.example.moneyusage.frontend.screens.home_screen

import androidx.compose.foundation.lazy.LazyListState
import com.example.moneyusage.SPLASH_SCREEN
import com.example.moneyusage.backend.models.Data
import com.example.moneyusage.backend.models.services.interf.AccountService
import com.example.moneyusage.backend.models.services.interf.StorageService
import com.example.moneyusage.frontend.screens.AppViewModel
import javax.inject.Inject

class HomeScreenViewModel@Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageService
): AppViewModel() {

    // Storage service
    val database = storageService.dataset

    fun onDialogAlertBtn(
        dataName: String,
        amount: Double,
        description: String,
        date: String
    ) {
        launchCatching {
            storageService.addData(Data(
                userId = accountService.currentUserId,
                dataName = dataName,
                amount = amount,
                description = description,
                date = date
            ))
        }
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