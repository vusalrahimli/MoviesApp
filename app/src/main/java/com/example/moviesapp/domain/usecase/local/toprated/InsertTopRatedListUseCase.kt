package com.example.moviesapp.domain.usecase.local.toprated

import com.example.moviesapp.data.model.dto.homepagedto.TopRatedDto
import com.example.moviesapp.domain.repository.MovieRepository

class InsertTopRatedListUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(listTopRated: List<TopRatedDto>) =
        movieRepository.insertTopRatedList(listTopRated)
}
