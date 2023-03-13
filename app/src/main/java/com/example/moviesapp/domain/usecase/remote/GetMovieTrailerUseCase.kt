package com.example.moviesapp.domain.usecase.remote

import com.example.moviesapp.data.model.moviedetailspage.trailer.TrailerModel
import com.example.moviesapp.domain.repository.MovieRepository
import com.example.moviesapp.util.Resource
import javax.inject.Inject

class GetMovieTrailerUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun execute(movieID: Int): Resource<List<TrailerModel>> =
        movieRepository.getMovieTrailer(movieID)
}
