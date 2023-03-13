package com.example.moviesapp.domain.usecase.local.popular

import androidx.lifecycle.LiveData
import com.example.moviesapp.data.model.dto.homepagedto.PopularDto
import com.example.moviesapp.domain.repository.MovieRepository

class GetAllPopularUseCase(private val movieRepository: MovieRepository) {
    fun execute(): LiveData<List<PopularDto>> =
        movieRepository.getAllPopular()
}
