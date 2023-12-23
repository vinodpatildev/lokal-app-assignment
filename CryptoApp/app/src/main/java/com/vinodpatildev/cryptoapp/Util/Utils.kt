package com.vinodpatildev.cryptoapp.Util

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.Date

fun roundTo6DecimalPlaces(value: Double): String {
    return BigDecimal(value).setScale(6, RoundingMode.HALF_EVEN).toString()
}

fun getCurrentTime(): String {
    val dateFormat = SimpleDateFormat("HH:mm:ss")
    val currentTimeMillis = System.currentTimeMillis()
    val date = Date(currentTimeMillis)
    return dateFormat.format(date)
}