package com.vinodpatildev.cryptoapp.Util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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

fun isNetworkAvailable(context: Context?):Boolean{
    if (context == null) return false
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            return true
        }
    }
    return false
}