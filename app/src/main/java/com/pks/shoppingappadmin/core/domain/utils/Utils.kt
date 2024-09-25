package com.pks.shoppingappadmin.core.domain.utils

fun isValidNumber(input: String): Boolean {
    return try {
        input.toDouble()
        false
    } catch (e: NumberFormatException) {
        true
    }
}