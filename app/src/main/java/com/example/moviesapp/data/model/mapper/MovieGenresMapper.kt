package com.example.moviesapp.data.model.mapper

import com.example.moviesapp.data.model.dto.moviedetaildto.MovieGenresDto
import com.example.moviesapp.data.model.moviedetailspage.moviedetails.Genre

fun Genre.toDto() = MovieGenresDto(
    id = id,
    genreName = name ?: "",
)
