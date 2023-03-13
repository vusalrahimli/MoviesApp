package com.example.moviesapp.data.model.mapper

import com.example.moviesapp.data.model.dto.moviedetaildto.MovieTrailerDto
import com.example.moviesapp.data.model.moviedetailspage.trailer.TrailerModel

fun TrailerModel.toDto() = MovieTrailerDto(
    id = id,
    key = key ?: "",
)
