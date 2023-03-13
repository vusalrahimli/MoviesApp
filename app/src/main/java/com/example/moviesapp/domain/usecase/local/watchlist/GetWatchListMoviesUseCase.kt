package com.example.moviesapp.domain.usecase.local.watchlist

import androidx.lifecycle.LiveData
import com.example.moviesapp.data.model.watchlist.WatchListModel
import com.example.moviesapp.domain.repository.MovieRepository

class GetWatchListMoviesUseCase(private val movieRepository: MovieRepository) {
    fun execute(): LiveData<List<WatchListModel>> =
        movieRepository.getWatchListMovies()
}
