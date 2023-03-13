package com.example.moviesapp.domain.usecase.local.upcoming

import androidx.lifecycle.LiveData
import com.example.moviesapp.data.model.dto.homepagedto.UpcomingDto
import com.example.moviesapp.domain.repository.MovieRepository

class GetAllUpcomingUseCase(private val movieRepository: MovieRepository) {
    fun execute(): LiveData<List<UpcomingDto>> =
        movieRepository.getAllUpcoming()
}
