package com.example.moneyusage.backend.models

import com.example.moneyusage.frontend.dataclasses.DateTime
import com.google.firebase.firestore.DocumentId

data class Data(
    @DocumentId val dataId: String = "",
    val userId: String? = null,
    val dataName: String? = null,
    val amount: Double = 0.0,
    val description: String = "",
    val date: DateTime? = null,
    val icon: Int? = null
)
