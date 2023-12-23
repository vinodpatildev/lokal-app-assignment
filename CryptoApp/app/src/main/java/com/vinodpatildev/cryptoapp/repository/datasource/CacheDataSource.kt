package com.vinodpatildev.cryptoapp.repository.datasource

import com.vinodpatildev.cryptoapp.models.CryptoApiResponseField

interface CacheDataSource {
    suspend fun saveCryptoCurrencyListToCache(cryptoCurrencyListMap : Map<String, CryptoApiResponseField> )
    suspend fun getCryptoCurrencyListFromCache() : Map<String, CryptoApiResponseField>
}