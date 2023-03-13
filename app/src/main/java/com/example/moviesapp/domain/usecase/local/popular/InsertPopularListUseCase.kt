package com.example.moviesapp.domain.usecase.local.popular

import com.example.moviesapp.data.model.dto.homepagedto.PopularDto
import com.example.moviesapp.domain.repository.MovieRepository

class InsertPopularListUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(listPopular: List<PopularDto>) =
        movieRepository.insertPopularList(listPopular)
}
