package com.example.moneyusage.helper

import android.annotation.SuppressLint

/**
 * Format a string as money
 * @return string containing money formatted as a string
 */
fun String.toMoneyFormat() : String{
    // Clean up the string
    val cleanString = this.replace("[^\\d.]".toRegex(), "")

    // Format the string
    val formattedString = cleanString.reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()

    if (formattedString.contains(".")) {
        val (money, cents) = cleanString.split(".")
        return "$money.$cents"
    }
    return formattedString
}

fun String.limitMoneyDigits(): String {
    val number = this.substringBefore(".").toDoubleOrNull() ?: return this

    return when {
        number >= 1_000_000_000 -> "${(number / 1_000_000_000)}B"
        number >= 1_000_000 -> "${(number / 1_000_000)}M"
        number >= 1_000 -> "${(number / 1_000)}K"
        else -> this
    }
}