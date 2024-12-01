package com.example.moneyusage.backend.models

import com.google.firebase.firestore.DocumentId

data class Data(
    val userId: String,
    @DocumentId val dataId: String,
    val dataName: String,
    val amount: Double,
    var description: String = "",
){
    fun toMap(): Map<String, Any> {
        return mapOf(
            "userId" to userId,
            "dataId" to dataId,
            "dataName" to dataName,
            "amount" to amount,
            "description" to description
        )
    }
}
