package com.example.moviesapp.data.model.mapper

import com.example.moviesapp.data.model.dto.homepagedto.NowPlayingDto
import com.example.moviesapp.data.model.homepage.nowplaying.NowPlayingModel

fun NowPlayingModel.toDto() = NowPlayingDto(
    backDropPath = backdrop_path ?: "",
    id = id,
    overview = overview ?: "",
    popularity = popularity ?: 0.0,
    posterPath = poster_path ?: "",
    releaseDate = release_date ?: "",
    title = title ?: "",
    rating = vote_average ?: 0.0,
    isSaved = isSaved,
)
