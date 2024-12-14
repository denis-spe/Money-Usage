package com.example.moneyusage.frontend.dataclasses

data class DialogAlertBtnListener(
    val income: () -> Unit,
    val expense: () -> Unit,
    val lent: () -> Unit,
    val debt: () -> Unit
)
