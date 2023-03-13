package com.example.moviesapp.data.model.mapper

import com.example.moviesapp.data.model.moviedetailspage.cast.CastModel
import com.example.moviesapp.data.model.dto.moviedetaildto.MovieCastDto

fun CastModel.toDto() = MovieCastDto(
    id = id,
    castID = cast_id,
    name = name ?: "",
    character = character ?: "",
    profilePath = profile_path ?: "",
)
