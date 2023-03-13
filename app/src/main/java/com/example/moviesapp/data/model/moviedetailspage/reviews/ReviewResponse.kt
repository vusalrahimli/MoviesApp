package com.example.moviesapp.data.model.moviedetailspage.reviews

data class ReviewResponse(
    val id: Int,
    val page: Int,
    val results: List<ReviewModel>,
    val total_pages: Int,
    val total_results: Int
)