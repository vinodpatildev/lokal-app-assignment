package com.vinodpatildev.cryptoapp.models

import com.google.gson.annotations.SerializedName

class CryptoCurrencyLiveExchangeRatesResponse (
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("rates")
    val rates: Map<String, Double>
)