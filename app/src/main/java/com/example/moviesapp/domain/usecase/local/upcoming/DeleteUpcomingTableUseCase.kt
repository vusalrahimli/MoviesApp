package com.example.moviesapp.domain.usecase.local.upcoming

import com.example.moviesapp.domain.repository.MovieRepository

class DeleteUpcomingTableUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute() = movieRepository.deleteUpcomingTable()
}
