package com.vinodpatildev.cryptoapp.repository.datasourceimpl

import com.vinodpatildev.cryptoapp.api.ApiService
import com.vinodpatildev.cryptoapp.models.CryptoCurrencyListResponse
import com.vinodpatildev.cryptoapp.models.CryptoCurrencyLiveExchangeRatesResponse
import com.vinodpatildev.cryptoapp.repository.datasource.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {
    override suspend fun getAllCryptoCurrencyList(): Response<CryptoCurrencyListResponse> {
        return apiService.getAllCryptoCurrencyList()
    }

    override suspend fun getCryptoCurrencyLive(): Response<CryptoCurrencyLiveExchangeRatesResponse> {
        return apiService.getCryptoCurrencyLive()
    }
}