package com.vinodpatildev.cryptoapp.models

import com.google.gson.annotations.SerializedName

data class CryptoCurrencyListResponse(
    @SerializedName("crypto")
    val crypto: Map<String, CryptoApiResponseField>
)
