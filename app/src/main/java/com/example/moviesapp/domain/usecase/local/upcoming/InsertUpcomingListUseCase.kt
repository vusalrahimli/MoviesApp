package com.example.moviesapp.domain.usecase.local.upcoming

import com.example.moviesapp.data.model.dto.homepagedto.UpcomingDto
import com.example.moviesapp.domain.repository.MovieRepository

class InsertUpcomingListUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(listUpcoming: List<UpcomingDto>) =
        movieRepository.insertUpcomingList(listUpcoming)
}
