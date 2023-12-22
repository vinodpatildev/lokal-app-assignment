package com.vinodpatildev.cryptoapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vinodpatildev.cryptoapp.Util.Resource
import com.vinodpatildev.cryptoapp.models.CryptoCurrency

class HomeViewModel(private val app: Application) : AndroidViewModel(app) {
    val cryptoCurrencyList : MutableLiveData<Resource<CryptoCurrency>> = MutableLiveData()
}