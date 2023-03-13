package com.example.moviesapp.domain.usecase.remote

import com.example.moviesapp.data.model.homepage.toprated.TopRatedModel
import com.example.moviesapp.domain.repository.MovieRepository
import com.example.moviesapp.util.Resource
import javax.inject.Inject

class GetTopRatedUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun execute(): Resource<List<TopRatedModel>> = movieRepository.getTopRated()

}