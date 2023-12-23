package com.vinodpatildev.cryptoapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vinodpatildev.cryptoapp.Util.Resource
import com.vinodpatildev.cryptoapp.models.CryptoCurrency
import com.vinodpatildev.cryptoapp.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val app: Application,
    private val repository: Repository
) : AndroidViewModel(app) {
    val cryptoCurrencyList: MutableLiveData<Resource<List<CryptoCurrency>>> = MutableLiveData()

    fun reloadCryptoCurrencyList() = viewModelScope.launch(Dispatchers.IO) {
        cryptoCurrencyList.postValue(Resource.Loading())
        try {
            val CurrencyList = repository.getCurrencyList().data
            Log.d("logtesttestt", "reloadCryptoCurrencyList: CurrencyList" + CurrencyList.toString())
            val exchangeRates = repository.getLiveExchangeRates().data
            Log.d("logtesttestt", "reloadCryptoCurrencyList: exchangeRates" + exchangeRates.toString())


            val combinedList: List<CryptoCurrency> = CurrencyList.orEmpty()
                .entries
                .mapNotNull { entry ->
                    val currencyCode = entry.key
                    val cryptoCurrencyListApiData = entry.value
                    val exchangeRate = exchangeRates?.get(currencyCode)
                    exchangeRate?.let {
                        CryptoCurrency(
                            icon_url = cryptoCurrencyListApiData.iconUrl,
                            exchange_rate = it,
                            name = cryptoCurrencyListApiData.name,
                            name_full = cryptoCurrencyListApiData.fullName,
                            symbol = cryptoCurrencyListApiData.symbol
                        )
                    }
                }
            cryptoCurrencyList.postValue(Resource.Success(combinedList))
        } catch (e: Exception) {
            Log.d("logtesttest", "exception : " + e.message.toString())
            cryptoCurrencyList.postValue(Resource.Error(e.message.toString()))
        }
    }
}