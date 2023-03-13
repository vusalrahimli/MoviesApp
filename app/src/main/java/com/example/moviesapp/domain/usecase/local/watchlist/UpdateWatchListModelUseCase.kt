package com.example.moviesapp.domain.usecase.local.watchlist

import com.example.moviesapp.data.model.watchlist.WatchListModel
import com.example.moviesapp.domain.repository.MovieRepository

class UpdateWatchListModelUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(watchListModel: WatchListModel) =
        movieRepository.updateWatchListModel(watchListModel)
}
