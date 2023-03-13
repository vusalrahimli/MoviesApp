package com.example.moviesapp.domain.usecase.local.nowplaying

import androidx.lifecycle.LiveData
import com.example.moviesapp.data.model.dto.homepagedto.NowPlayingDto
import com.example.moviesapp.domain.repository.MovieRepository

class GetAllNowPlayingUseCase(private val movieRepository: MovieRepository) {

    fun execute(): LiveData<List<NowPlayingDto>> =
        movieRepository.getAllNowPlaying()
}
