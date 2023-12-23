package com.vinodpatildev.cryptoapp.repository.datasourceimpl

import com.vinodpatildev.cryptoapp.api.ApiService
import com.vinodpatildev.cryptoapp.models.CryptoApiResponseField
import com.vinodpatildev.cryptoapp.models.CryptoCurrency
import com.vinodpatildev.cryptoapp.repository.datasource.CacheDataSource

class CacheDataSourceImpl : CacheDataSource {
    private var cryptoCurrencyListMapCache = mutableMapOf<String, CryptoApiResponseField>()
    override suspend fun saveCryptoCurrencyListToCache(cryptoCurrencyListMap: Map<String, CryptoApiResponseField>) {
        cryptoCurrencyListMapCache.clear()
        cryptoCurrencyListMapCache.putAll(cryptoCurrencyListMap)
    }

    override suspend fun getCryptoCurrencyListFromCache(): Map<String, CryptoApiResponseField> {
        return cryptoCurrencyListMapCache
    }

}