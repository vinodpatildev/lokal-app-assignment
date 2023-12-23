package com.vinodpatildev.cryptoapp.repository.datasource

import com.vinodpatildev.cryptoapp.models.CryptoCurrencyListResponse
import com.vinodpatildev.cryptoapp.models.CryptoCurrencyLiveExchangeRatesResponse
import retrofit2.Response

interface  RemoteDataSource {
    suspend fun getAllCryptoCurrencyList() : Response<CryptoCurrencyListResponse>
    suspend fun getCryptoCurrencyLive() : Response<CryptoCurrencyLiveExchangeRatesResponse>
}