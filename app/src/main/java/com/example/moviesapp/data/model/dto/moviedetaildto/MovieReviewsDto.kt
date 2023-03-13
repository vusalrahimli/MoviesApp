package com.example.moviesapp.data.model.dto.moviedetaildto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieReviewsDto(
    val id: String,
    val comment: String,
    val avatarPath: String,
    val createdAt: String,
    val author: String,
) : Parcelable
