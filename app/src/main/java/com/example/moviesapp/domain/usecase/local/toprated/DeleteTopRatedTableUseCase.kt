package com.example.moviesapp.domain.usecase.local.toprated

import com.example.moviesapp.domain.repository.MovieRepository

class DeleteTopRatedTableUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute() = movieRepository.deleteTopRatedTable()
}
