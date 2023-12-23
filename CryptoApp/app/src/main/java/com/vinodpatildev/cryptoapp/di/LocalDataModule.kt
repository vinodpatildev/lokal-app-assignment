package com.vinodpatildev.cryptoapp.presentation.di

import com.vinodpatildev.cryptoapp.repository.datasource.LocalDataSource
import com.vinodpatildev.cryptoapp.repository.datasourceimpl.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(): LocalDataSource {
        return LocalDataSourceImpl()
    }
}