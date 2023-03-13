package com.example.moviesapp.domain.usecase.remote

import com.example.moviesapp.data.model.homepage.nowplaying.NowPlayingModel
import com.example.moviesapp.domain.repository.MovieRepository
import com.example.moviesapp.util.Resource
import javax.inject.Inject

class GetNowPlayingUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun execute(): Resource<List<NowPlayingModel>> = movieRepository.getNowPlaying()
}
