package com.vinodpatildev.cryptoapp.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.vinodpatildev.cryptoapp.Util.Resource
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

    private fun isNetworkAvailable(context: Context?):Boolean{
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    override suspend fun getCurrencyList(): Resource<Map<String, CryptoApiResponseField>> {
        Log.d("logtesttest", "getCurrencyList: called")
        if(isNetworkAvailable(appContext)){
            Log.d("logtesttest", "getCurrencyList: to initiate the network call ")
            var response = remoteDataSource.getAllCryptoCurrencyList()
            if(response.isSuccessful){
                response.body()?.let{result->
                    // TODO Save in database or cache
                    val eventList = Resource.Success(result.crypto)
                    return eventList
                }
            }else{
                return Resource.Error(response.message())
            }
        }
        Log.d("InternetDebug", "getCurrencyList: Internet Gone")
        throw IllegalStateException("Internet not connected")
        return Resource.Error("Internet is not available.")
    }

    override suspend fun getLiveExchangeRates(): Resource<Map<String, Double>> {
        if(isNetworkAvailable(appContext)){
            var response = remoteDataSource.getCryptoCurrencyLive()
            if(response.isSuccessful){
                response.body()?.let{result->
                    val eventList = Resource.Success(result.rates)
                    return eventList
                }
            }else{
                return Resource.Error(response.message())
            }
        }
        return Resource.Error("Internet is not available.")
    }
}