package com.example.moviesapp.domain.usecase.remote

import com.example.moviesapp.data.model.moviedetailspage.moviedetails.MovieDetailsResponse
import com.example.moviesapp.domain.repository.MovieRepository
import com.example.moviesapp.util.Resource
import javax.inject.Inject

class GetMovieDurationUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun execute(movieID: Int): Resource<MovieDetailsResponse> =
        movieRepository.getMovieDuration(movieID)
}
