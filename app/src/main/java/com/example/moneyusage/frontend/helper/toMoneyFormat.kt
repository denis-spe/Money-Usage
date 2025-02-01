package com.example.moneyusage.frontend.helper

import java.math.BigDecimal
import kotlin.math.ceil
import kotlin.math.pow

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
    return formattedString
}

/**
 * Limit the number of digits in a string to 4
 */
fun String.limitMoneyDigits(): String {

    if (this.contains("E", ignoreCase = true)) {
        val numberBeforeE = this.substringBefore("E").toDouble()
            .roundUpToDecimalPlaces(2)
        val numberAfterE = this.substringAfter("E").toInt()

        return when (numberAfterE) {
            in (7 ..8) -> "${numberBeforeE}M" // Million
            in 9..12 -> "${numberBeforeE}B" // Billion
            in 12..14 -> "${numberBeforeE}T" // Trillion
            in 15..17 -> "${numberBeforeE}Q" // Quadrillion
            in 18..20 -> "${numberBeforeE}S" // Quintillion
            else -> this // Default fallback
        }
    }

    if (this.length > 4) {
        return if (this.contains(".")){
            val decimalPart = this.substringAfter(".")
            val integerPart = this.substringBefore(".")
            val formattedIntegerPart = integerPart.toMoneyFormat()
            "$formattedIntegerPart.$decimalPart"
        }
        else this.toMoneyFormat()
    }
    return this
}

fun Double.toMoneyFormat(limitMillionAndHundred: Boolean = false) : String {
    val amountSymbol = this.toString()

    // Handle the whole number.
    var wholeNumber = amountSymbol.substringBefore(".")

    if (wholeNumber.length < 7 && !amountSymbol.contains("E")) {
        wholeNumber = if (wholeNumber.length > 3) wholeNumber.toMoneyFormat() else wholeNumber

        // Handle the after decimal number.
        var afterDecimalNumber = amountSymbol.substringAfter(".")
        afterDecimalNumber = if (afterDecimalNumber.length > 2)
            afterDecimalNumber.substring(0, 2)
        else afterDecimalNumber
        return "$wholeNumber.$afterDecimalNumber"
    }
    return amountSymbol.limitMoneyDigits()
}

fun Double.roundUpToDecimalPlaces(decimalPlaces: Int): Double {
    val factor = 10.0.pow(decimalPlaces)
    return ceil(this * factor) / factor
}

val String.clearUpCommas: Double get() {
    return this.replace(",", "")
        .toDouble()
}