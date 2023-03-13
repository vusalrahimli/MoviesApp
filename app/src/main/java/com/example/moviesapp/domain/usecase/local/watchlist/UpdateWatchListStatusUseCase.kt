package com.example.moviesapp.domain.usecase.local.watchlist

import com.example.moviesapp.data.model.dto.homepagedto.NowPlayingDto
import com.example.moviesapp.data.model.dto.homepagedto.PopularDto
import com.example.moviesapp.data.model.dto.homepagedto.TopRatedDto
import com.example.moviesapp.data.model.dto.homepagedto.UpcomingDto
import com.example.moviesapp.domain.repository.MovieRepository

class UpdateWatchListStatusUseCase(private val movieRepository: MovieRepository) {

    suspend fun execute(dtoNP: NowPlayingDto) =
        movieRepository.updateWatchListStatus(dtoNP)

    suspend fun execute(dtoUpcoming: UpcomingDto) =
        movieRepository.updateWatchListStatus(dtoUpcoming)

    suspend fun execute(dtoTopRated: TopRatedDto) =
        movieRepository.updateWatchListStatus(dtoTopRated)

    suspend fun execute(dtoPopular: PopularDto) =
        movieRepository.updateWatchListStatus(dtoPopular)
}
