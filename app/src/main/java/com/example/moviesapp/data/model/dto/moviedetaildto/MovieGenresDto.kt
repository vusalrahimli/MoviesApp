package com.example.moviesapp.data.model.dto.moviedetaildto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieGenresDto(
    val id: Int,
    val genreName: String,
) : Parcelable
