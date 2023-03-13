package com.example.moviesapp.data.model.homepage.popular

data class PopularResponse(
    val page: Int,
    val results: List<PopularModel>,
    val total_pages: Int,
    val total_results: Int
)