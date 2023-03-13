package com.example.moviesapp.data.model.homepage.toprated

data class TopRatedResponse(
    val page: Int,
    val results: List<TopRatedModel>,
    val total_pages: Int,
    val total_results: Int
)