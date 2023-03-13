package com.example.moviesapp.di

import com.example.moviesapp.data.repository.MovieRepositoryImpl
import com.example.moviesapp.data.repository.datasource.LocalDataSource
import com.example.moviesapp.data.repository.datasource.RemoteDataSource
import com.example.moviesapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
    ): MovieRepository {
        return MovieRepositoryImpl(remoteDataSource, localDataSource)
    }
}
