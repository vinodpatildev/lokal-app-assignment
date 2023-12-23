package com.vinodpatildev.cryptoapp.Util

import java.math.BigDecimal
import java.math.RoundingMode

fun roundTo6DecimalPlaces(value: Double): String {
    return BigDecimal(value).setScale(6, RoundingMode.HALF_EVEN).toString()
}