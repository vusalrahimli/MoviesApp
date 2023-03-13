package com.example.moviesapp.domain.usecase.local.watchlist

import com.example.moviesapp.data.model.watchlist.WatchListModel
import com.example.moviesapp.domain.repository.MovieRepository

class GetWatchListMovieByIdUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(movieID: Int): WatchListModel? =
        movieRepository.getWatchListMovieById(movieID)
}
