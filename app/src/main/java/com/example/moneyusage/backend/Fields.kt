package com.example.moneyusage.backend

/**
 * Fields for the firebase realtime database
 * @param income List of income data.
 * @param expense List of expense data.
 * @param lend List of lend data.
 * @param debt List of debt data.
 */
data class Fields(
    val income: List<Data?> = emptyList(),
    val expense: List<Data?> = emptyList(),
    val lend: List<Data?> = emptyList(),
    val debt: List<Data?> = emptyList(),
)
