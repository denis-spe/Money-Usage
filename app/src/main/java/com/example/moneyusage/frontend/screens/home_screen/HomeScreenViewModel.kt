package com.example.moneyusage.frontend.screens.home_screen

import android.util.Log
import androidx.compose.runtime.collectAsState
import com.example.moneyusage.backend.models.Data
import com.example.moneyusage.backend.models.services.impl.StorageServiceImpl
import com.example.moneyusage.backend.models.services.interf.AccountService
import com.example.moneyusage.backend.models.services.interf.StorageService
import com.example.moneyusage.frontend.screens.AppViewModel
import javax.inject.Inject

class HomeScreenViewModel@Inject constructor(
    private val accountService: AccountService,
    private val storage: StorageService
): AppViewModel() {

    // Storage service
    val database = storage.dataset

    fun onProfileClick() {
        launchCatching {
            storage.addData(Data(
                accountService.currentUserId,
                "",
                "Income",
                8.5443,
                "Income from work"
            ))
        }
    }
}