package com.example.moviesapp.di

import android.content.Context
import androidx.room.Room
import com.example.moviesapp.data.db.MovieDatabase
import com.example.moviesapp.data.other.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        Constants.DB_NAME,
    ).allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun provideMovieDao(
        database: MovieDatabase,
    ) = database.movieDao()
}
