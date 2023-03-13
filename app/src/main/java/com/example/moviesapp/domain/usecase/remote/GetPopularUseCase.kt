package com.example.moviesapp.domain.usecase.remote

import com.example.moviesapp.data.model.homepage.popular.PopularModel
import com.example.moviesapp.domain.repository.MovieRepository
import com.example.moviesapp.util.Resource
import javax.inject.Inject

class GetPopularUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun execute(): Resource<List<PopularModel>> = movieRepository.getPopular()

}