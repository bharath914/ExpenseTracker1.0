package com.bharath.expensetracker.presentation.screens.addscreen

fun cleanFloatingNumberString(input: String): Float? {
    // Define the regular expression pattern to match characters to be removed.
    val regexPattern = "[,\\s]".toRegex()

    // Replace all matched characters with an empty string.
    val cleanedString = input.replace(regexPattern, "")

    return try {
        // Convert the cleaned string back to a floating-point number.
        cleanedString.toFloat()
    } catch (e: NumberFormatException) {
        // If the conversion fails, return null or handle the error accordingly.
        null
    }
}
