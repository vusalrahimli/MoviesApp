package com.example.moviesapp.domain.usecase.local.toprated

import androidx.lifecycle.LiveData
import com.example.moviesapp.data.model.dto.homepagedto.TopRatedDto
import com.example.moviesapp.domain.repository.MovieRepository

class GetAllTopRatedUseCase(private val movieRepository: MovieRepository) {
    fun execute(): LiveData<List<TopRatedDto>> =
        movieRepository.getAllTopRated()
}
