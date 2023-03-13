package com.example.moviesapp.domain.usecase.local.popular

import com.example.moviesapp.domain.repository.MovieRepository

class DeletePopularTableUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute() = movieRepository.deletePopularTable()
}
