package com.vinodpatildev.cryptoapp.di

import android.content.Context
import com.vinodpatildev.cryptoapp.repository.Repository
import com.vinodpatildev.cryptoapp.repository.RepositoryImpl
import com.vinodpatildev.cryptoapp.repository.datasource.CacheDataSource
import com.vinodpatildev.cryptoapp.repository.datasource.LocalDataSource
import com.vinodpatildev.cryptoapp.repository.datasource.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        @ApplicationContext appContext : Context,
        cacheDataSource: CacheDataSource,
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) : Repository {
        return RepositoryImpl(appContext, cacheDataSource, localDataSource, remoteDataSource)
    }
}