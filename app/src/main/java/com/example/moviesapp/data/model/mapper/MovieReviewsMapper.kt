package com.example.moviesapp.data.model.mapper

import com.example.moviesapp.data.model.dto.moviedetaildto.MovieReviewsDto
import com.example.moviesapp.data.model.moviedetailspage.reviews.ReviewModel

fun ReviewModel.toDto() = MovieReviewsDto(
    id = id,
    createdAt = created_at ?: "",
    author = author ?: "",
    comment = content ?: "",
    avatarPath = author_details?.avatar_path ?: "",
)
