package com.example.moneyusage.frontend.helper

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
fun String.limitMoneyDigits(
    limitMillonAndHundred: Boolean = false
): String {
    val amountSymbol: String
    val wholeNumber: String
    var afterDecimalNumber: String
    val fullMoneyFormat: String

    if (this.contains("E")){
        // Get the amount symbol
        amountSymbol = when(this.substringAfter("E").toInt()){
            7 -> "M"
            8 -> "M"
            9 -> "B"
            10 -> "B"
            11 -> "B"
            12 -> "T"
            else -> ""
        }
        // Take the whole number, after decimal and amount symbol
        wholeNumber = this.substringBefore(".")

        // Take out the number after the decimal point then before the exponent
        afterDecimalNumber = this.substringAfter(".")
            .substringBefore("E")

        // Limit the number of digits
        afterDecimalNumber = afterDecimalNumber.substring(0, 2)

        // Combine the whole number, after decimal and amount symbol
        fullMoneyFormat = "$wholeNumber.${afterDecimalNumber}$amountSymbol"
    } else {
        fullMoneyFormat = if (this.substringBefore(".").length == 7
            && limitMillonAndHundred)
            ((this.toDouble() / 1_000_000).toInt()).toString() + "M"
        else
            this.substringBefore(".").toMoneyFormat()
    }

    return fullMoneyFormat
}