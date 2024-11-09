package com.example.moneyusage.backend

/**
 * Data to add in the fields
 * @param amount The amount of the transaction.
 * @param date The date of the transaction.
 * @param description The description of the transaction.
 */
data class Data(
    val amount: Double,
    val date: String,
    val description: String?,
)
