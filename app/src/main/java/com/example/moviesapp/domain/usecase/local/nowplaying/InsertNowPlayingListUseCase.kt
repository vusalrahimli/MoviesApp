package com.example.moviesapp.domain.usecase.local.nowplaying

import com.example.moviesapp.data.model.dto.homepagedto.NowPlayingDto
import com.example.moviesapp.domain.repository.MovieRepository

class InsertNowPlayingListUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(listNP: List<NowPlayingDto>) =
        movieRepository.insertNowPlayingList(listNP)
}
