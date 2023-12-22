package com.vinodpatildev.cryptoapp.di

import android.app.Application
import com.vinodpatildev.cryptoapp.viewmodels.HomeViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideHomeViewModelFactory(app : Application) : HomeViewModelFactory {
        return HomeViewModelFactory(app)
    }

}