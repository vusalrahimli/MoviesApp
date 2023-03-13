package com.example.moviesapp.di

import com.example.moviesapp.data.api.API
import com.example.moviesapp.data.db.MovieDao
import com.example.moviesapp.data.repository.datasource.LocalDataSource
import com.example.moviesapp.data.repository.datasource.RemoteDataSource
import com.example.moviesapp.data.repository.datasourceimpl.LocalDataSourceImpl
import com.example.moviesapp.data.repository.datasourceimpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: API): RemoteDataSource {
        return RemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun providesLocalDataSource(movieDao: MovieDao): LocalDataSource {
        return LocalDataSourceImpl(movieDao)
    }
}

