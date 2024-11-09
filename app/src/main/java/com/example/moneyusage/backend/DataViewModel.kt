package com.example.moneyusage.backend

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class DataViewModel(
    private val collectionName: String,
    private val documentName: String
): ViewModel() {
    private val dataRepository = DataRepository(collectionName, documentName)

    // Function to get data and expose it as LiveData
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    fun getData(fieldName: String): LiveData<Any?> {
        return dataRepository.getData(fieldName)
    }
}