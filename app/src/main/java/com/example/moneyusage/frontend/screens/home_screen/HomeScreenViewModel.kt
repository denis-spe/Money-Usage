package com.example.moneyusage.frontend.screens.home_screen

import com.example.moneyusage.SPLASH_SCREEN
import com.example.moneyusage.backend.models.Data
import com.example.moneyusage.backend.models.services.impl.StorageServiceImpl
import com.example.moneyusage.backend.models.services.interf.AccountService
import com.example.moneyusage.frontend.dataclasses.DateTime
import com.example.moneyusage.frontend.screens.AppViewModel
import javax.inject.Inject

class HomeScreenViewModel@Inject constructor(
    private val accountService: AccountService,
    private val storageService: StorageServiceImpl
): AppViewModel() {

    // Storage service
    val database = storageService.dataset

    fun onDateSaveClick(
        dataName: String,
        amount: Double,
        description: String,
        date: DateTime,
        icon: Int,
        isItPayed: Boolean? = null,
        lentTo: String? = null,
        debtFrom: String? = null
    ) {
        launchCatching {
            storageService.addData(Data(
                userId = accountService.currentUserId,
                category = dataName,
                amount = amount,
                description = description,
                date = date,
                icon = icon,
                isItPayed = isItPayed,
                lentTo = lentTo,
                debtFrom = debtFrom
            ))
        }
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