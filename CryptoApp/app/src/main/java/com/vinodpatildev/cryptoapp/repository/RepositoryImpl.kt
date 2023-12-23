package com.vinodpatildev.cryptoapp.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.vinodpatildev.cryptoapp.Util.Resource
import com.vinodpatildev.cryptoapp.Util.isNetworkAvailable
import com.vinodpatildev.cryptoapp.models.CryptoApiResponseField
import com.vinodpatildev.cryptoapp.repository.datasource.CacheDataSource
import com.vinodpatildev.cryptoapp.repository.datasource.LocalDataSource
import com.vinodpatildev.cryptoapp.repository.datasource.RemoteDataSource
import kotlin.math.log

class RepositoryImpl(
    private val appContext: Context,
    private val cacheDataSource: CacheDataSource,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    ) : Repository {

    override suspend fun getCurrencyList(): Resource<Map<String, CryptoApiResponseField>> {
        if(isNetworkAvailable(appContext)){
            // Try to get it from cache
            val cryptoCurrencyListMap = cacheDataSource.getCryptoCurrencyListFromCache()
            if(cryptoCurrencyListMap.size > 0) return Resource.Success(cryptoCurrencyListMap)

            var response = remoteDataSource.getAllCryptoCurrencyList()
            if(response.isSuccessful){
                response.body()?.let{result->
                    cacheDataSource.saveCryptoCurrencyListToCache(result.crypto)
                    return Resource.Success(result.crypto)
                }
            }else{
                return Resource.Error(response.message())
            }
        }
        throw IllegalStateException("Internet not connected")
        return Resource.Error("Internet is not available.")
    }

    override suspend fun getLiveExchangeRates(): Resource<Map<String, Double>> {
        if(isNetworkAvailable(appContext)){
            var response = remoteDataSource.getCryptoCurrencyLive()
            if(response.isSuccessful){
                response.body()?.let{result->
                    return Resource.Success(result.rates)
                }
            }else{
                return Resource.Error(response.message())
            }
        }
        throw IllegalStateException("Internet not connected")
        return Resource.Error("Internet is not available.")
    }
}