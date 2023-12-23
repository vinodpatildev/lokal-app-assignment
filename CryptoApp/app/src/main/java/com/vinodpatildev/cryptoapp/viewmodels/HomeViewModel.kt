package com.vinodpatildev.cryptoapp.viewmodels

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vinodpatildev.cryptoapp.Util.Constant
import com.vinodpatildev.cryptoapp.Util.Resource
import com.vinodpatildev.cryptoapp.Util.getCurrentTime
import com.vinodpatildev.cryptoapp.models.CryptoApiResponseField
import com.vinodpatildev.cryptoapp.models.CryptoCurrency
import com.vinodpatildev.cryptoapp.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeoutException

class HomeViewModel(
    private val app: Application,
    private val repository: Repository
) : AndroidViewModel(app) {
    private val handler = Handler(Looper.getMainLooper())

    val cryptoCurrencyList: MutableLiveData<Resource<List<CryptoCurrency>>> = MutableLiveData()
    val lastUpdatedTime: MutableLiveData<String> = MutableLiveData("")

    init {
        refreshCryptoCurrencyList()
    }

    private fun refreshCryptoCurrencyList() {
        viewModelScope.launch(Dispatchers.IO) {
            cryptoCurrencyList.postValue(Resource.Loading())
            try {
                val currencyList = repository.getCurrencyList().data
                val exchangeRates = repository.getLiveExchangeRates().data
                val combinedList: List<CryptoCurrency> = combineLists(currencyList, exchangeRates)
                cryptoCurrencyList.postValue(Resource.Success(combinedList))
                updateLastUpdateTime()
                // refresh after 3 minutes
                handler.postDelayed({ refreshCryptoCurrencyList() }, Constant.NEXT_REFRESTH_INTERVAL)
            } catch (e: Exception) {
                cryptoCurrencyList.postValue(Resource.Error(e.message.toString()))
                if (e is TimeoutException) refreshCryptoCurrencyList()
            }
        }
    }

    fun reloadCryptoCurrencyList() {
        viewModelScope.launch(Dispatchers.IO) {
            cryptoCurrencyList.postValue(Resource.Loading())
            try {
                val currencyList = repository.getCurrencyList().data
                val exchangeRates = repository.getLiveExchangeRates().data
                val combinedList: List<CryptoCurrency> = combineLists(currencyList, exchangeRates)
                cryptoCurrencyList.postValue(Resource.Success(combinedList))
                updateLastUpdateTime()
            } catch (e: Exception) {
                cryptoCurrencyList.postValue(Resource.Error(e.message.toString()))
                if (e is TimeoutException) reloadCryptoCurrencyList()
            }
        }
    }

    private fun updateLastUpdateTime() {
        lastUpdatedTime.postValue(getCurrentTime())
    }

    private fun combineLists(CurrencyList :  Map<String, CryptoApiResponseField>?, exchangeRates :  Map<String, Double>?) : List<CryptoCurrency>{
        return CurrencyList.orEmpty()
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
    }
}