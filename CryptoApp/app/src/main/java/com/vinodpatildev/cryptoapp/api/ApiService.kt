package com.vinodpatildev.cryptoapp.api

import com.vinodpatildev.cryptoapp.BuildConfig
import com.vinodpatildev.cryptoapp.models.CryptoCurrencyListResponse
import com.vinodpatildev.cryptoapp.models.CryptoCurrencyLiveExchangeRatesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("list")
    suspend fun getAllCryptoCurrencyList(
        @Query("access_key")
        accessKey:String = BuildConfig.CRYPTO_API_ACCESS_KEY
    ) : Response<CryptoCurrencyListResponse>

    @GET("live")
    suspend fun getCryptoCurrencyLive(
        @Query("access_key")
        accessKey:String = BuildConfig.CRYPTO_API_ACCESS_KEY
    ) : Response<CryptoCurrencyLiveExchangeRatesResponse>

}