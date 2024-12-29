package com.example.moneyusage.frontend.dataclasses

import java.time.Year

data class DateTime (
    val year: Int? = null,
    val month: String? = null,
    val day: Int? = null,
    val hour: Int? = null,
    val minute: Int? = null,
    val dayOfTheWeek: String? = null
)