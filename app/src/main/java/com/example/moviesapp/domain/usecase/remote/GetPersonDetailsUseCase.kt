package com.example.moviesapp.domain.usecase.remote

import com.example.moviesapp.data.model.moviedetailspage.cast.persondetails.PersonDetailsResponse
import com.example.moviesapp.domain.repository.MovieRepository
import com.example.moviesapp.util.Resource
import javax.inject.Inject

class GetPersonDetailsUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun execute(personID: Int): Resource<PersonDetailsResponse> =
        movieRepository.getPersonDetails(personID)
}
