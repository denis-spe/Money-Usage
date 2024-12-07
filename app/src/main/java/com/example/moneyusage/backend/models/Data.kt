package com.example.moneyusage.backend.models

import com.google.firebase.firestore.DocumentId

data class Data(
    @DocumentId val dataId: String = "",
    val userId: String? = null,
    val dataName: String? = null,
    val amount: Double? = null,
    val description: String = "",
)
