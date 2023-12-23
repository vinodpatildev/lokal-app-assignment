package com.vinodpatildev.cryptoapp.repository

import com.vinodpatildev.cryptoapp.Util.Resource
import com.vinodpatildev.cryptoapp.models.CryptoApiResponseField

interface Repository {
    suspend fun getCurrencyList() : Resource<Map<String, CryptoApiResponseField>>
    suspend fun getLiveExchangeRates() : Resource<Map<String, Double>>
}