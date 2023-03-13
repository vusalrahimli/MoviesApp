package com.example.moviesapp.domain.usecase.local.nowplaying

import com.example.moviesapp.domain.repository.MovieRepository

class DeleteNowPlayingTableUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute() = movieRepository.deleteNowPlayingTable()
}
