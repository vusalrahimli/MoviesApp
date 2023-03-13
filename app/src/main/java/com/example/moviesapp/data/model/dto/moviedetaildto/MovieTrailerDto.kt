package com.example.moviesapp.data.model.dto.moviedetaildto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieTrailerDto(
    val id: String,
    val key: String,
) : Parcelable
