package com.vinodpatildev.cryptoapp.di

import com.vinodpatildev.cryptoapp.api.ApiService
import com.vinodpatildev.cryptoapp.repository.datasource.RemoteDataSource
import com.vinodpatildev.cryptoapp.repository.datasourceimpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService) : RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }
}