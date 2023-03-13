package com.example.moviesapp.data.model.dto.moviedetaildto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCastDto(
    val id: Int,
    val castID: Int,
    val name: String,
    val character: String,
    val profilePath: String,
) : Parcelable
