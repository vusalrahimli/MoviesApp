package com.example.moviesapp.data.model.mapper

import com.example.moviesapp.data.model.dto.moviedetaildto.MovieDurationDto
import com.example.moviesapp.data.model.moviedetailspage.moviedetails.MovieDetailsResponse

fun MovieDetailsResponse.toDto() = MovieDurationDto(
    duration = runtime ?: 0,
)
