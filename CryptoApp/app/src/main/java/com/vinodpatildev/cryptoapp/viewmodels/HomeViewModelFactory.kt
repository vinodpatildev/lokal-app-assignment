package com.vinodpatildev.cryptoapp.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vinodpatildev.cryptoapp.repository.Repository

class HomeViewModelFactory(
    private val app: Application,
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            app,
            repository
        ) as T
    }
}