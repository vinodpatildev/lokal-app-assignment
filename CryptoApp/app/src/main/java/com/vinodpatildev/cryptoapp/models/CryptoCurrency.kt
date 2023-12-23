package com.vinodpatildev.cryptoapp.models

data class CryptoCurrency(
    val icon_url: String,
    val exchange_rate : Double,
    val name: String,
    val name_full: String,
    val symbol: String
)
