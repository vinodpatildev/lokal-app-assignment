package com.vinodpatildev.cryptoapp.models

data class CryptoCurrency(
    val icon_url: String,
    val max_supply: Int,
    val name: String,
    val name_full: String,
    val symbol: String
)